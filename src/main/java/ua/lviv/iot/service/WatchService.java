package ua.lviv.iot.service;

import ua.lviv.iot.model.Owner;
import ua.lviv.iot.model.Watch;

import java.util.List;

public interface WatchService extends GeneralService<Watch, String> {

    List<Owner> findAllWatchOwnersBySerialNumber(String serialNumber);
}
