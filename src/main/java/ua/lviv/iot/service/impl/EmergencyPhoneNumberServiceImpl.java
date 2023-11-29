package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.EmergencyPhoneNumberNotFoundException;
import ua.lviv.iot.model.EmergencyPhoneNumber;
import ua.lviv.iot.repository.EmergencyPhoneNumberRepository;
import ua.lviv.iot.service.EmergencyPhoneNumberService;

import java.util.List;

@Service
public class EmergencyPhoneNumberServiceImpl implements EmergencyPhoneNumberService {

    private static final String EMERGENCY_PHONE_NUMBER_NOT_FOUND_EXCEPTION_MESSAGE = "Phone number was not found!";

    @Autowired
    private EmergencyPhoneNumberRepository repository;

    @Override
    public List<EmergencyPhoneNumber> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public EmergencyPhoneNumber save(EmergencyPhoneNumber entity) {
        return repository.save(entity);
    }

    @Override
    public EmergencyPhoneNumber findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmergencyPhoneNumberNotFoundException(
                EMERGENCY_PHONE_NUMBER_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Transactional
    @Override
    public EmergencyPhoneNumber update(EmergencyPhoneNumber entity, Long id) {
        EmergencyPhoneNumber emergencyPhoneNumber = findById(id);
        emergencyPhoneNumber.setId(id);
        emergencyPhoneNumber.setPhoneNumber(entity.getPhoneNumber());
        emergencyPhoneNumber.setWatch(entity.getWatch());
        return repository.save(emergencyPhoneNumber);
    }

    @Transactional
    @Override
    public EmergencyPhoneNumber deleteById(Long id) {
        EmergencyPhoneNumber emergencyPhoneNumber = findById(id);
        repository.deleteById(id);
        return emergencyPhoneNumber;
    }

}
