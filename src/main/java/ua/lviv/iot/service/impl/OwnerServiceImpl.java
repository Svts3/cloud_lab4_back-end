package ua.lviv.iot.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.OwnerNotFoundException;
import ua.lviv.iot.model.Owner;
import ua.lviv.iot.model.PropertyInfo;
import ua.lviv.iot.model.Watch;
import ua.lviv.iot.repository.OwnerRepository;
import ua.lviv.iot.repository.PropertyInfoRepository;
import ua.lviv.iot.service.OwnerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private static final String OWNER_NOT_FOUND_EXCEPTION_MESSAGE = "Owner was not found!";

    private OwnerRepository ownerRepository;

    private PropertyInfoRepository propertyInfoRepository;


    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository,
                            PropertyInfoRepository propertyInfoRepository) {
        super();
        this.ownerRepository = ownerRepository;
        this.propertyInfoRepository = propertyInfoRepository;
    }

    @Transactional
    @Override
    public void ownerInsertion() {
        ownerRepository.ownerInsertion();
    }

    @Transactional
    @Override
    public void createDatabasesForEachOwner() {
        ownerRepository.createDatabasesForEachOwner();

    }

    @Transactional
    @Override
    public Owner save(Owner entity) {
        return ownerRepository.save(entity);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(OWNER_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Transactional
    @Override
    public Owner update(Owner entity, Long id) {
        Owner owner = findById(id);
        owner.setId(id);
        owner.setFirstName(entity.getFirstName());
        owner.setLastName(entity.getLastName());
        owner.setDateOfBirth(entity.getDateOfBirth());
        owner.setGender(entity.getGender());
        owner.setPropertyInfos(entity.getPropertyInfos());
        return ownerRepository.save(owner);
    }

    @Transactional
    @Override
    public Owner deleteById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(OWNER_NOT_FOUND_EXCEPTION_MESSAGE));
        ownerRepository.deleteById(id);
        return owner;
    }

    @Override
    public List<Owner> findByFirstName(String firstName) {
        return ownerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Watch> findAllWatchesByOwnerId(Long ownerId) {
        List<PropertyInfo> propertyInfos = propertyInfoRepository.findByOwnerId(ownerId);
        List<Watch> watches = propertyInfos.stream().map((n) -> n.getWatch())
                .collect(Collectors.toList());

        return watches;
    }

    @Override
    public Owner findByEmail(String email) throws Exception {
        return ownerRepository.findByEmail(email)
                .orElseThrow(()->new Exception(String.format("User with email [%s] was not found!", email)));
    }

    @Override
    public Boolean existsByEmail(String email) throws Exception {
        return ownerRepository.existsByEmail(email);
    }


}
