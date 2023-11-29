package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.WatchBatteryNotFoundException;
import ua.lviv.iot.model.WatchBattery;
import ua.lviv.iot.repository.WatchBatteryRepository;
import ua.lviv.iot.service.WatchBatteryService;

import java.util.List;

@Service
public class WatchBatteryServiceImpl implements WatchBatteryService {

    private static final String WATCH_BATTERY_NOT_FOUND_EXCEPTION_MESSAGE = "Watch battery was not found";

    private WatchBatteryRepository repository;

    @Autowired
    public WatchBatteryServiceImpl(WatchBatteryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WatchBattery> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public WatchBattery save(WatchBattery entity) {
        return repository.save(entity);
    }

    @Override
    public WatchBattery findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new WatchBatteryNotFoundException(WATCH_BATTERY_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Transactional
    @Override
    public WatchBattery update(WatchBattery entity, Long id) {
        WatchBattery watchBattery = findById(id);
        watchBattery.setId(id);
        watchBattery.setChargeLevel(entity.getChargeLevel());
        watchBattery.setWatch(entity.getWatch());
        return repository.save(watchBattery);
    }

    @Transactional
    @Override
    public WatchBattery deleteById(Long id) {
        WatchBattery watchBattery = findById(id);
        repository.deleteById(id);
        return watchBattery;
    }

}
