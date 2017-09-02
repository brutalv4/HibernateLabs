package ua.skillsup.practice.hibernate.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.practice.hibernate.converters.EntityDtoConverter;
import ua.skillsup.practice.hibernate.dao.ItemDao;
import ua.skillsup.practice.hibernate.dao.entity.Item;
import ua.skillsup.practice.hibernate.dao.entity.User;
import ua.skillsup.practice.hibernate.model.ItemDto;
import ua.skillsup.practice.hibernate.model.filter.ItemFilter;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ua.skillsup.practice.hibernate.converters.EntityDtoConverter.convert;

/**
 * Created by oleksii on 10/10/15.
 */
@Repository
public class ItemDaoImpl implements ItemDao {

	private final SessionFactory sessionFactory;

    @Autowired
    public ItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<ItemDto> findAll() {
        List<Item> items = sessionFactory.getCurrentSession()
                .createQuery("from ua.skillsup.practice.hibernate.dao.entity.Item").list();
        ArrayList<ItemDto> result = new ArrayList<>(items.size());

        return items.stream().map(EntityDtoConverter::convert).collect(toList());
	}

    @Transactional(readOnly = true)
	public ItemDto findById(long id) {
        Item byId = (Item) sessionFactory.getCurrentSession().get(Item.class, id);

        return convert(byId);
	}

	public ItemDto findByTitle(String title) {
		return null;
	}

	public List<ItemDto> findByFilter(ItemFilter filter) {
		return null;
	}

	public long create(ItemDto itemDto) {
		return 0;
	}

	public void update(ItemDto itemDto) {

	}
}