package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.StreetNotFoundException;
import ua.lviv.iot.model.Street;
import ua.lviv.iot.repository.StreetRepository;
import ua.lviv.iot.service.StreetService;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {

    private static final String STREET_NOT_FOUND_EXCEPTION_MESSAGE = "Street was not found!";

    private StreetRepository repository;

    @Autowired
    public StreetServiceImpl(StreetRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Street> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Street save(Street entity) {
        return repository.save(entity);
    }

    @Override
    public Street findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new StreetNotFoundException(STREET_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Street update(Street entity, String id) {
        Street street = findById(id);
        repository.deleteById(id);
        street.setName(id);
        street.setCity(entity.getCity());
        street.setWatch(entity.getWatch());
        return repository.save(street);
    }

    @Transactional
    @Override
    public Street deleteById(String id) {
        Street street = findById(id);
        repository.deleteById(id);
        return street;
    }


}
