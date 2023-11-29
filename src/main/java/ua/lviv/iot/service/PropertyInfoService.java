package ua.lviv.iot.service;

import ua.lviv.iot.model.PropertyInfo;

public interface PropertyInfoService extends GeneralService<PropertyInfo, Long> {
    void propertyInfoInsetion(Long ownerId, String serial_number);
}
