package _020_Executor;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import future.with.spring.LoginHistory;
import future.with.spring.LoginHistoryDAO;
import future.with.spring.User;
import future.with.spring.UserDAO;

public class _099_Spring {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("i-async-future.xml");
		String userId = "admin";
		UserDAO userDAO = (UserDAO) context.getBean("userDao");
		User user = userDAO.findUser(userId);

		LoginHistoryDAO loginHistoryDao = (LoginHistoryDAO) context.getBean("loginHistoryDao");

		Future<Boolean> future = loginHistoryDao.recordLoginHistory(user);
		
        while (!future.isDone()) {
            System.out.println(Thread.currentThread().getName() + " :Task is not completed yet....");
            Thread.sleep(100); //sleep for sometime before checking again
        }
		
		List<LoginHistory> loginHistoryList = loginHistoryDao.findLoginHistory(user);
		for (LoginHistory loginHistory : loginHistoryList) {
			System.out.println(Thread.currentThread().getName() + " : " + loginHistory);
		}
		((AbstractApplicationContext)context).registerShutdownHook();
	}
}