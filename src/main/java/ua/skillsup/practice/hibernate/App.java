package ua.skillsup.practice.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.skillsup.practice.hibernate.dao.ItemDao;
import ua.skillsup.practice.hibernate.dao.UserDao;
import ua.skillsup.practice.hibernate.model.ItemDto;
import ua.skillsup.practice.hibernate.model.filter.ItemFilter;
import ua.skillsup.practice.hibernate.service.AuctionService;

/**
 * Created by oleksii on 10/10/15.
 */
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		ItemDao itemDao = context.getBean(ItemDao.class);
		UserDao userDao = context.getBean(UserDao.class);

		ItemFilter filter = new ItemFilter();
		filter.setWeightFrom(0.1);
		filter.setWeightTo(6.0);

		itemDao.findByFilter(filter).forEach(System.out::println);


		AuctionService service = context.getBean(AuctionService.class);

		context.stop();
	}
}
