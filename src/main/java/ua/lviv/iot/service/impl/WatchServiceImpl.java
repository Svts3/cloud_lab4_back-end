package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.WatchNotFoundException;
import ua.lviv.iot.model.Owner;
import ua.lviv.iot.model.PropertyInfo;
import ua.lviv.iot.model.Watch;
import ua.lviv.iot.repository.PropertyInfoRepository;
import ua.lviv.iot.repository.WatchRepository;
import ua.lviv.iot.service.WatchService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchServiceImpl implements WatchService {

    private static final String WATCH_NOT_FOUND_EXCEPTION_MESSAGE = "Watch was not found!";

    private WatchRepository watchRepository;

    private PropertyInfoRepository propertyInfoRepository;

    @Autowired
    public WatchServiceImpl(WatchRepository repository, PropertyInfoRepository propertyInfo) {
        this.watchRepository = repository;
        this.propertyInfoRepository = propertyInfo;
    }

    @Override
    public List<Watch> findAll() {
        return watchRepository.findAll();
    }

    @Transactional
    @Override
    public Watch save(Watch entity) {
        return watchRepository.save(entity);
    }

    @Override
    public Watch findById(String id) {
        return watchRepository.findById(id)
                .orElseThrow(() -> new WatchNotFoundException(WATCH_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Transactional
    @Override
    public Watch update(Watch entity, String id) {
        Watch watch = findById(id);
        watch.setSerialNumber(entity.getSerialNumber());
        watch.setEmergencyPhoneNumbers(entity.getEmergencyPhoneNumbers());
        watch.setHealthInfos(entity.getHealthInfos());
        watch.setPropertyInfos(entity.getPropertyInfos());
        watch.setStreet(entity.getStreet());
        watch.setWatchBatteries(entity.getWatchBatteries());
        watch.setWatchLocations(watch.getWatchLocations());
        return watchRepository.save(watch);
    }

    @Transactional
    @Override
    public Watch deleteById(String id) {

        Watch watch = watchRepository.findById(id)
                .orElseThrow(() -> new WatchNotFoundException(WATCH_NOT_FOUND_EXCEPTION_MESSAGE));
        watchRepository.deleteById(id);
        return watch;
    }

    @Override
    public List<Owner> findAllWatchOwnersBySerialNumber(String serialNumber) {
        List<PropertyInfo> propertyInfos = propertyInfoRepository
                .findPropertyInfoBySerialNumber(serialNumber);
        List<Owner> owners = propertyInfos.stream().map((n) -> n.getOwner())
                .collect(Collectors.toList());

        return owners;
    }


}
