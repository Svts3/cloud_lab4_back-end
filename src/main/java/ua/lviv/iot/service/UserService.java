package ua.lviv.iot.service;

import ua.lviv.iot.model.User;

import java.util.List;

public interface UserService extends GeneralService<User, Long> {

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);
}
