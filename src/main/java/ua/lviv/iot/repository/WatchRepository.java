package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Watch;

import java.util.Optional;

@Repository
public interface WatchRepository extends JpaRepository<Watch, String> {

    Optional<Watch> findByStreetName(String streetName);
}
