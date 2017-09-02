package ua.skillsup.practice.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.skillsup.practice.hibernate.dao.ItemDao;
import ua.skillsup.practice.hibernate.dao.LotDao;
import ua.skillsup.practice.hibernate.dao.UserDao;
import ua.skillsup.practice.hibernate.model.ItemDto;
import ua.skillsup.practice.hibernate.model.LotDto;
import ua.skillsup.practice.hibernate.model.filter.ItemFilter;
import ua.skillsup.practice.hibernate.service.AuctionService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by oleksii on 10/10/15.
 */
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
		ItemDao itemDao = context.getBean(ItemDao.class);
		UserDao userDao = context.getBean(UserDao.class);
		LotDao lotDao = context.getBean(LotDao.class);
//
//		AuctionService service = context.getBean(AuctionService.class);
//
//		LotDto odinLot = service.createLot("Odin", "Gladiator, digital copy", BigDecimal.valueOf(500L), 5);
//		System.out.println(odinLot);
//
//        List<LotDto> all = lotDao.findAll();

        itemDao.findByCategories(Collections.singleton("Table"));

        context.stop();
	}
}
