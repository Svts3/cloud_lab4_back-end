package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, String> {

}
