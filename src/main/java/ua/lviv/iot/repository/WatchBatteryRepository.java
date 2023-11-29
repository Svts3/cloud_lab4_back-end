package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.WatchBattery;

@Repository
public interface WatchBatteryRepository extends JpaRepository<WatchBattery, Long> {

}
