package ua.lviv.iot.service;

import ua.lviv.iot.model.HealthInfo;

public interface HealthInfoService extends GeneralService<HealthInfo, Long> {
    Integer getAverageHeartbeatRate();
}
