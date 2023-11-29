package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.OwnerDTO;
import ua.lviv.iot.dto.WatchDTO;
import ua.lviv.iot.dto.assembler.OwnerDTOAssembler;
import ua.lviv.iot.dto.assembler.WatchDTOAssembler;
import ua.lviv.iot.model.Owner;
import ua.lviv.iot.model.Watch;
import ua.lviv.iot.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    private OwnerService ownerService;
    private OwnerDTOAssembler ownerDTOAssembler;
    private WatchDTOAssembler watchDTOAssembler;

    @Autowired
    public OwnerController(OwnerService ownerService, OwnerDTOAssembler ownerDTOAssembler,
                           WatchDTOAssembler watchDTOAssembler) {
        this.ownerService = ownerService;
        this.ownerDTOAssembler = ownerDTOAssembler;
        this.watchDTOAssembler = watchDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<OwnerDTO>> findAll() {
        List<Owner> owners = ownerService.findAll();
        CollectionModel<OwnerDTO> collectionModel = ownerDTOAssembler.toCollectionModel(owners);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable("id") Long id) {
        Owner onwer = ownerService.findById(id);
        OwnerDTO ownerDTO = ownerDTOAssembler.toModel(onwer);
        return new ResponseEntity<>(ownerDTO, HttpStatus.OK);
    }

    @GetMapping("/db")
    public ResponseEntity<OwnerDTO> createDatabasesForEachOwner() {
        ownerService.createDatabasesForEachOwner();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fname/{firstName}")
    public ResponseEntity<CollectionModel<OwnerDTO>> findByFirstName(
            @PathVariable(name = "firstName", required = true) String firstname) {
        List<Owner> owners = ownerService.findByFirstName(firstname);
        CollectionModel<OwnerDTO> collectionModel = ownerDTOAssembler.toCollectionModel(owners);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/lname/{lastName}")
    public ResponseEntity<CollectionModel<OwnerDTO>> findByLastName(
            @PathVariable(name = "lastName", required = true) String lastName) {
        List<Owner> owners = ownerService.findByLastName(lastName);
        CollectionModel<OwnerDTO> collectionModel = ownerDTOAssembler.toCollectionModel(owners);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{ownerId}/watches")
    public ResponseEntity<CollectionModel<WatchDTO>> findAllWatchesByOwnerId(
            @PathVariable(name = "ownerId", required = true) Long ownerId) {
        List<Watch> watches = ownerService.findAllWatchesByOwnerId(ownerId);
        CollectionModel<WatchDTO> collectionModel = watchDTOAssembler.toCollectionModel(watches);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<OwnerDTO> addOwner(@RequestBody Owner owner) {
        Owner owner2 = ownerService.save(owner);
        OwnerDTO dto = ownerDTOAssembler.toModel(owner2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/ownerInsertion")
    public ResponseEntity<OwnerDTO> insert10Owners() {
        ownerService.ownerInsertion();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable("ownerId") Long ownerId,
                                                @RequestBody Owner owner) {
        Owner owner2 = ownerService.update(owner, ownerId);
        OwnerDTO dto = ownerDTOAssembler.toModel(owner2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<OwnerDTO> deleteOwner(@PathVariable("ownerId") Long ownerId) {
        Owner owner2 = ownerService.deleteById(ownerId);
        OwnerDTO dto = ownerDTOAssembler.toModel(owner2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
