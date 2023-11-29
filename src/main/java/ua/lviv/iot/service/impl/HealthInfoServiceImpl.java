package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.HealthInfoNotFoundException;
import ua.lviv.iot.model.HealthInfo;
import ua.lviv.iot.repository.HealthInfoRepository;
import ua.lviv.iot.service.HealthInfoService;

import java.util.List;

@Service
public class HealthInfoServiceImpl implements HealthInfoService {

    private static final String HEALTH_INFO_NOT_FOUND_EXCEPTION_MESSAGE = "Health info was not found!";

    private HealthInfoRepository repository;

    @Autowired
    public HealthInfoServiceImpl(HealthInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HealthInfo> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public HealthInfo save(HealthInfo entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Integer getAverageHeartbeatRate() {
        return repository.getAverageHeartbeatRate();
    }

    @Override
    public HealthInfo findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new HealthInfoNotFoundException(HEALTH_INFO_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Transactional
    @Override
    public HealthInfo update(HealthInfo entity, Long id) {
        HealthInfo healthInfo = findById(id);
        healthInfo.setId(id);
        healthInfo.setHeartbeatRate(entity.getHeartbeatRate());
        healthInfo.setWatch(entity.getWatch());
        return repository.save(healthInfo);
    }

    @Transactional
    @Override
    public HealthInfo deleteById(Long id) {
        HealthInfo healthInfo = findById(id);
        repository.deleteById(id);
        return healthInfo;
    }

}
