package ua.lviv.iot.service;

import ua.lviv.iot.model.Country;
import ua.lviv.iot.model.Region;

import java.util.List;

public interface CountryService extends GeneralService<Country, String> {

    List<Region> findAllRegionsByCountryName(String countryName);
}
