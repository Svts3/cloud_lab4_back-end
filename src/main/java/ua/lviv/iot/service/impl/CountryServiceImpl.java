package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.CountryNotFoundException;
import ua.lviv.iot.model.Country;
import ua.lviv.iot.model.Region;
import ua.lviv.iot.repository.CountryRepository;
import ua.lviv.iot.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String COUNTRY_NOT_FOUND_EXCEPTION_MESSAGE = "County was not found!";

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Transactional
    @Override
    public Country save(Country entity) {
        return countryRepository.save(entity);
    }

    @Override
    public Country findById(String id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new CountryNotFoundException(COUNTRY_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Country update(Country entity, String id) {
        Country country = findById(id);
        country.setName(entity.getName());
        country.setContinent_name(entity.getContinent_name());
        country.setRegions(entity.getRegions());
        return countryRepository.save(country);
    }

    @Transactional
    @Override
    public Country deleteById(String id) {
        Country country = countryRepository.findById(id).orElseThrow(
                () -> new CountryNotFoundException(COUNTRY_NOT_FOUND_EXCEPTION_MESSAGE));
        countryRepository.deleteById(id);
        return country;
    }

    @Override
    public List<Region> findAllRegionsByCountryName(String countryName) {
        Country country = findById(countryName);
        return country.getRegions();
    }

}
