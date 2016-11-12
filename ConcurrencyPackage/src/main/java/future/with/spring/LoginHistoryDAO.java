package future.with.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class LoginHistoryDAO extends JdbcDaoSupport {
    
	@Async
	public Future<Boolean> recordLoginHistory(User user) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " :Add entry to login history for " + user.getUserId());
		List<Map<String, Object>> i = getJdbcTemplate().queryForList("select BJ_SEQUENCE.nextval from dual");
        getJdbcTemplate().update("insert into bj_user_login_history(id,status, session_id, login_time, user_id) values(?,?, ?, ?, ?)",
        		i.iterator().next().get("NEXTVAL"),"SUCCESS", user.getSessionId(), user.getLoginTime(), user.getId());
        Thread.sleep(1000);
        return new AsyncResult<Boolean>(true);
    }
     
    public List<LoginHistory> findLoginHistory(final User user) {
        return getJdbcTemplate().query("select id, status, login_time, session_id from bj_user_login_history where user_id=?", 
                new Object[]{user.getId()}, 
                new RowMapper<LoginHistory>(){
 
            public LoginHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setId(rs.getInt("id"));
                loginHistory.setStatus(rs.getString("status"));
                loginHistory.setLoginTime(rs.getDate("login_time"));
                loginHistory.setSessionId(rs.getLong("session_id"));
                loginHistory.setUser(user);
                return loginHistory;
            }});
    }    
}
