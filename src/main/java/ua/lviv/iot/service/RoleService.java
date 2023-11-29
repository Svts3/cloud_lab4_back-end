package ua.lviv.iot.service;

import ua.lviv.iot.exception.RoleNotFoundException;
import ua.lviv.iot.model.Role;

import java.util.List;

public interface RoleService extends GeneralService<Role, Long> {
    Role findByName(String name) throws RoleNotFoundException;

}
