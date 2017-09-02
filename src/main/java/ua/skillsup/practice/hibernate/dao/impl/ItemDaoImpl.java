package ua.skillsup.practice.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.practice.hibernate.converters.EntityDtoConverter;
import ua.skillsup.practice.hibernate.dao.ItemDao;
import ua.skillsup.practice.hibernate.dao.entity.Item;
import ua.skillsup.practice.hibernate.model.ItemDto;
import ua.skillsup.practice.hibernate.model.filter.ItemFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Transactional(readOnly = true)
	public ItemDto findByTitle(String title) {
		Item item = (Item) sessionFactory.getCurrentSession()
				.createQuery("select i from Item i where i.title = :title")
				.setParameter("title", title).uniqueResult();

		return convert(item);
	}

	@Transactional(readOnly = true)
	public List<ItemDto> findByFilter(ItemFilter filter) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
		Optional.ofNullable(filter.getHeightFrom())
				.ifPresent(value -> criteria.add(Restrictions.ge("height", value)));

		Optional.ofNullable(filter.getHeightTo())
				.ifPresent(value -> criteria.add(Restrictions.le("height", value)));

		Optional.ofNullable(filter.getWidthFrom())
				.ifPresent(value -> criteria.add(Restrictions.ge("width", value)));

		Optional.ofNullable(filter.getWidthTo())
				.ifPresent(value -> criteria.add(Restrictions.le("width", value)));

		Optional.ofNullable(filter.getWeightFrom())
				.ifPresent(value -> criteria.add(Restrictions.ge("weight", value)));

		Optional.ofNullable(filter.getWeightTo())
				.ifPresent(value -> criteria.add(Restrictions.le("weight", value)));


		List<Item> items = criteria.list();
		List<ItemDto> result = new ArrayList<>(items.size());
		for (Item item : items) {
			result.add(convert(item));
		}

		return result;
	}

	public long create(ItemDto itemDto) {
		return 0;
	}

	public void update(ItemDto itemDto) {

	}
}