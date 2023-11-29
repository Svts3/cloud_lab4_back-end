package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.ContinentNotFoundException;
import ua.lviv.iot.model.Continent;
import ua.lviv.iot.repository.ContinentRepository;
import ua.lviv.iot.service.ContinentService;

import java.util.List;

@Service
public class ContinentServiceImpl implements ContinentService {

    private static final String CONTINENT_NOT_FOUND_EXCEPTION_MESSAGE = "Continent with such name was not found!";

    private ContinentRepository continentRepository;

    @Autowired
    public ContinentServiceImpl(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    @Override
    public List<Continent> findAll() {
        return continentRepository.findAll();
    }

    @Override
    public Continent save(Continent entity) {
        return continentRepository.save(entity);
    }

    @Override
    public Continent findById(String id) {
        return continentRepository.findById(id).orElseThrow(
                () -> new ContinentNotFoundException(CONTINENT_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Continent update(Continent entity, String id) {
        Continent continent = findById(id);
        continentRepository.deleteById(id);
        continent.setName(entity.getName());
        return continentRepository.save(entity);
    }

    @Override
    public Continent deleteById(String id) {
        Continent continent = findById(id);
        continentRepository.deleteById(id);
        return continent;
    }

    @Override
    public void continentInsertion(String name) {
        continentRepository.continentInsertion(name);
    }

}
