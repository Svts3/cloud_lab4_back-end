package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.EmergencyPhoneNumber;

@Repository
public interface EmergencyPhoneNumberRepository
        extends JpaRepository<EmergencyPhoneNumber, Long> {

}
