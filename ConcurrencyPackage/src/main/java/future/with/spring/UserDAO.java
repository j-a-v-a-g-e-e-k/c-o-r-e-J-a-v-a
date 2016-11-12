package future.with.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDAO extends JdbcDaoSupport {
	
	public User findUser(String userId) {
		return getJdbcTemplate().query("select id, user_id, password from bj_user_details where user_id=?",
				new Object[] { userId },
				new ResultSetExtractor<User>() {
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		});
	}
}
