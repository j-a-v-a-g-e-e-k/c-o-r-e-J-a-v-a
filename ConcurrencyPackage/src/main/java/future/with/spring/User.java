package future.with.spring;

import java.util.Date;
import java.util.UUID;
 
public class User {
    private Integer id;
    private String userId;
    private String password;
    private Date loginTime;
    private Long sessionId;
 
    public User(Integer id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.sessionId = UUID.randomUUID().getMostSignificantBits();
        this.loginTime = new Date();
    }
 
    public Integer getId() {
        return id;
    }
 
    public String getUserId() {
        return userId;
    }
 
    public String getPassword() {
        return password;
    }
 
    public Date getLoginTime() {
        return loginTime;
    }
 
    public Long getSessionId() {
        return sessionId;
    }
     
}