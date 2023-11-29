package ua.lviv.iot.service;

import ua.lviv.iot.model.Owner;
import ua.lviv.iot.model.Watch;

import java.util.List;
import java.util.Optional;

public interface OwnerService extends GeneralService<Owner, Long> {

    List<Owner> findByFirstName(String firstName);

    List<Owner> findByLastName(String lastName);

    List<Watch> findAllWatchesByOwnerId(Long ownerId);

    Owner findByEmail(String email) throws Exception;

    Boolean existsByEmail(String email)throws Exception;

    void ownerInsertion();

    void createDatabasesForEachOwner();
}
