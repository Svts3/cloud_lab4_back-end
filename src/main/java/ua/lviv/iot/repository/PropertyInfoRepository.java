package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.PropertyInfo;

import java.util.List;

@Repository
public interface PropertyInfoRepository extends JpaRepository<PropertyInfo, Long> {

    @Query(value = "SELECT * FROM property_info p WHERE p.owner_id=:owner_id", nativeQuery = true)
    List<PropertyInfo> findByOwnerId(@Param("owner_id") Long ownerId);

    @Query(value = "SELECT * FROM property_info p WHERE p.watch_serial_number=:watch_serial_number", nativeQuery = true)
    List<PropertyInfo> findPropertyInfoBySerialNumber(
            @Param("watch_serial_number") String serialNumber);

    @Procedure(procedureName = "propertyInfoInsetion")
    void propertyInfoInsetion(Long ownerId, String serial_number);


}
