package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.WatchLocationNotFoundException;
import ua.lviv.iot.model.WatchLocation;
import ua.lviv.iot.repository.WatchLocationRepository;
import ua.lviv.iot.service.WatchLocationService;

import java.util.List;

@Service
public class WatchLocationServiceImpl implements WatchLocationService {

    private static final String WATCH_LOCATION_NOT_FOUND_EXCEPTION_MESSAGE = "Health info was not found!";

    private WatchLocationRepository repository;

    @Autowired
    public WatchLocationServiceImpl(WatchLocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WatchLocation> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public WatchLocation save(WatchLocation entity) {
        return repository.save(entity);
    }

    @Override
    public WatchLocation findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new WatchLocationNotFoundException(
                WATCH_LOCATION_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Transactional
    @Override
    public WatchLocation update(WatchLocation entity, Long id) {
        WatchLocation watchLocation = findById(id);
        watchLocation.setId(id);
        watchLocation.setLatitude(entity.getLatitude());
        watchLocation.setLongitude(entity.getLongitude());
        watchLocation.setWatch(entity.getWatch());
        return repository.save(watchLocation);
    }

    @Transactional
    @Override
    public WatchLocation deleteById(Long id) {
        WatchLocation watchLocation = findById(id);
        repository.deleteById(id);
        return watchLocation;
    }

}
