package web.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.models.User;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        User updatedUser = userDao.getUserById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setSecondName(user.getSecondName());
        updatedUser.setAge(user.getAge());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        userDao.updateUser(updatedUser);

    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            userDao.deleteUser(user);
        }
    }
}
