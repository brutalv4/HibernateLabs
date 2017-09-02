package ua.skillsup.practice.hibernate.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.practice.hibernate.converters.EntityDtoConverter;
import ua.skillsup.practice.hibernate.dao.UserDao;
import ua.skillsup.practice.hibernate.dao.entity.User;
import ua.skillsup.practice.hibernate.model.UserDto;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ua.skillsup.practice.hibernate.converters.EntityDtoConverter.convert;

/**
 * Created by oleksii on 10/10/15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("from ua.skillsup.practice.hibernate.dao.entity.User").list();
        ArrayList<UserDto> result = new ArrayList<>(users.size());

        return users.stream().map(EntityDtoConverter::convert).collect(toList());
	}

    @Transactional(readOnly = true)
	public UserDto findById(long id) {
        User byId = (User) sessionFactory.getCurrentSession().get(User.class, id);

        return convert(byId);
    }

	public UserDto findByLogin(String login) {
		return null;
	}

	public long create(UserDto userDto) {
		return 0;
	}

	public void update(UserDto userDto) {

	}
}