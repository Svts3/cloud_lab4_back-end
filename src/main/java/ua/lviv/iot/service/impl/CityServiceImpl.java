package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.CityNotFoundException;
import ua.lviv.iot.model.City;
import ua.lviv.iot.model.Street;
import ua.lviv.iot.repository.CityRepository;
import ua.lviv.iot.service.CityService;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private static final String CITY_NOT_FOUND_EXCEPTION = "City was not found!";

    private CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public City save(City entity) {
        return repository.save(entity);
    }

    @Override
    public City findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(CITY_NOT_FOUND_EXCEPTION));
    }

    @Override
    public City update(City entity, String id) {
        City city = findById(id);
        repository.deleteById(id);
        city.setName(entity.getName());
        city.setRegion(entity.getRegion());
        entity.setStreets(entity.getStreets());
        return repository.save(entity);
    }

    @Transactional
    @Override
    public City deleteById(String id) {
        City city = findById(id);
        repository.deleteById(id);
        return city;
    }

    @Override
    public List<Street> findAllStreetsByCityName(String cityName) {
        City city = findById(cityName);
        return city.getStreets();
    }

}
