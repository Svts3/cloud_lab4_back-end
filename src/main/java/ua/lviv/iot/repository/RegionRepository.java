package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {

}
