package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.UserNotFoundException;
import ua.lviv.iot.model.User;
import ua.lviv.iot.repository.UserRepository;
import ua.lviv.iot.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User was not found!";

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Transactional
    @Override
    public User update(User entity, Long id) {
        User user = findById(id);
        user.setId(id);
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setDateOfBirth(entity.getDateOfBirth());
        user.setGender(entity.getGender());
        user.setPropertyInfo(entity.getPropertyInfo());
        return repository.save(user);
    }

    @Transactional
    @Override
    public User deleteById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE));
        repository.deleteById(id);
        return user;
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

}
