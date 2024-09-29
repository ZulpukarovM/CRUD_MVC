package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void update(long id, String name, String lastName, int age) {
        User user = userDao.getUser(id);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userDao.update(user);

    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(long id){
        return userDao.getUser(id);
    }
}
