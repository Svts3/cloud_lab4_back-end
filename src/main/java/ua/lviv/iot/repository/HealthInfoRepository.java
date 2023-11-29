package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.HealthInfo;

@Repository
public interface HealthInfoRepository extends JpaRepository<HealthInfo, Long> {

    @Procedure(procedureName = "print_average_heartbeat_rate")
    Integer getAverageHeartbeatRate();
}
