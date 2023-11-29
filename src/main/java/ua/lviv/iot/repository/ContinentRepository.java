package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import ua.lviv.iot.model.Continent;

public interface ContinentRepository extends JpaRepository<Continent, String> {

    @Procedure(procedureName = "continentInsertion")
    void continentInsertion(String name);

}
