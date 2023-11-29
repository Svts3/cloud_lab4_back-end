package ua.lviv.iot.service;

import ua.lviv.iot.model.City;
import ua.lviv.iot.model.Street;

import java.util.List;

public interface CityService extends GeneralService<City, String> {

    List<Street> findAllStreetsByCityName(String cityName);
}
