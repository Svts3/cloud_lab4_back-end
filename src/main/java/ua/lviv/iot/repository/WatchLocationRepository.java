package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.WatchLocation;

@Repository
public interface WatchLocationRepository extends JpaRepository<WatchLocation, Long> {

}
