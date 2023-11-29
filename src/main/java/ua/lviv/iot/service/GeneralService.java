package ua.lviv.iot.service;

import ua.lviv.iot.exception.RoleNotFoundException;

import java.util.List;

public interface GeneralService<T, ID> {

    List<T> findAll();

    T save(T entity);

    T findById(ID id);

    T update(T entity, ID id);

    T deleteById(ID id);
}
