package ua.lviv.iot.service;

import ua.lviv.iot.model.City;
import ua.lviv.iot.model.Region;

import java.util.List;

public interface RegionService extends GeneralService<Region, String> {

    List<City> findAllCitiesByRegionName(String regionName);
}
