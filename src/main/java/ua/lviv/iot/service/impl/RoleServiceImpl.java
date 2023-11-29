package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.RoleNotFoundException;
import ua.lviv.iot.model.Role;
import ua.lviv.iot.repository.RoleRepository;
import ua.lviv.iot.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Role findById(Long aLong) {
        try {
            return roleRepository.findById(aLong).orElseThrow(
                    () -> new RoleNotFoundException("Role was not found!"));
        } catch (RoleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role update(Role entity, Long aLong) {
        Role role = findById(aLong);
        role.setName(entity.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role deleteById(Long aLong){
        Role role = findById(aLong);
        roleRepository.deleteById(aLong);
        return role;
    }

    @Override
    public Role findByName(String name) throws RoleNotFoundException {
        return roleRepository.findByName(name).orElseThrow(
                () -> new RoleNotFoundException("Role was not found!"));
    }
}
