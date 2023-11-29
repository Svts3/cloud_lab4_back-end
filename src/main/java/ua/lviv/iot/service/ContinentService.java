package ua.lviv.iot.service;

import ua.lviv.iot.model.Continent;

public interface ContinentService extends GeneralService<Continent, String> {

    void continentInsertion(String name);

}
