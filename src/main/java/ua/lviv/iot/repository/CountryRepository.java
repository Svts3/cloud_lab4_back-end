package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

}
