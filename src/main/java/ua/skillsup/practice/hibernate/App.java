package ua.skillsup.practice.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.skillsup.practice.hibernate.dao.ItemDao;
import ua.skillsup.practice.hibernate.dao.UserDao;
import ua.skillsup.practice.hibernate.service.AuctionService;

/**
 * Created by oleksii on 10/10/15.
 */
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		ItemDao itemDao = context.getBean(ItemDao.class);
		System.out.println(itemDao.findAll());
		System.out.println();
		System.out.println(itemDao.findById(1));

		UserDao userDao = context.getBean(UserDao.class);
		System.out.println(userDao.findAll());
		System.out.println();
		userDao.findById(1);

		AuctionService service = context.getBean(AuctionService.class);

		context.stop();
	}
}
