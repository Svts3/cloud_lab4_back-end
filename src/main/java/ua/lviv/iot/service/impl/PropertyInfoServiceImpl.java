package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.PropertyInfoNotFoundException;
import ua.lviv.iot.model.PropertyInfo;
import ua.lviv.iot.repository.PropertyInfoRepository;
import ua.lviv.iot.service.PropertyInfoService;

import java.util.List;

@Service
public class PropertyInfoServiceImpl implements PropertyInfoService {

    private static final String PropertyInfoNotFoundException = "Property info was not found";

    private PropertyInfoRepository repository;

    @Autowired
    public PropertyInfoServiceImpl(PropertyInfoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public PropertyInfo save(PropertyInfo entity) {
        return repository.save(entity);
    }

    @Override
    public void propertyInfoInsetion(Long ownerId, String serial_number) {
        repository.propertyInfoInsetion(ownerId, serial_number);
    }

    @Override
    public PropertyInfo findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new PropertyInfoNotFoundException(PropertyInfoNotFoundException));
    }

    @Transactional
    @Override
    public PropertyInfo update(PropertyInfo entity, Long id) {
        PropertyInfo info = this.findById(id);
        info.setId(id);
        info.setOwner(entity.getOwner());
        info.setUsers(entity.getUsers());
        info.setWatch(entity.getWatch());
        return repository.save(info);
    }

    @Transactional
    @Override
    public PropertyInfo deleteById(Long id) {

        PropertyInfo info = repository.findById(id).orElseThrow(
                () -> new PropertyInfoNotFoundException(PropertyInfoNotFoundException));
        repository.deleteById(id);
        return info;
    }

    @Override
    public List<PropertyInfo> findAll() {
        return repository.findAll();
    }

}
