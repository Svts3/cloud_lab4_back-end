package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.WatchLocationDTO;
import ua.lviv.iot.dto.assembler.WatchLocationDTOAssembler;
import ua.lviv.iot.model.WatchLocation;
import ua.lviv.iot.service.WatchLocationService;

import java.util.List;

@RestController
@RequestMapping("/api/watchLocation")
public class WatchLocationController {

    private WatchLocationService watchLocationService;
    private WatchLocationDTOAssembler watchLocationDTOAssembler;

    @Autowired
    public WatchLocationController(WatchLocationService watchLocationService,
                                   WatchLocationDTOAssembler watchLocationDTOAssembler) {
        this.watchLocationService = watchLocationService;
        this.watchLocationDTOAssembler = watchLocationDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<WatchLocationDTO>> findAll() {
        List<WatchLocation> watchLocations = watchLocationService.findAll();
        CollectionModel<WatchLocationDTO> collectionModel = watchLocationDTOAssembler
                .toCollectionModel(watchLocations);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchLocationDTO> findById(@PathVariable("id") Long id) {
        WatchLocation watchLocation = watchLocationService.findById(id);
        WatchLocationDTO watchLocationDTO = watchLocationDTOAssembler.toModel(watchLocation);
        return new ResponseEntity<>(watchLocationDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<WatchLocationDTO> addWatchLocation(
            @RequestBody WatchLocation watchLocation) {

        WatchLocation watchLocation2 = watchLocationService.save(watchLocation);
        WatchLocationDTO watchLocationDTO = watchLocationDTOAssembler.toModel(watchLocation2);
        return new ResponseEntity<>(watchLocationDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchLocationDTO> updateOwner(@PathVariable("id") Long id,
                                                        @RequestBody WatchLocation watchLocation) {

        WatchLocation watchLocation2 = watchLocationService.update(watchLocation, id);
        WatchLocationDTO watchLocationDTO = watchLocationDTOAssembler.toModel(watchLocation2);
        return new ResponseEntity<>(watchLocationDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WatchLocationDTO> deleteOwner(@PathVariable("id") Long id) {
        WatchLocation watchLocation2 = watchLocationService.deleteById(id);
        WatchLocationDTO watchLocationDTO = watchLocationDTOAssembler.toModel(watchLocation2);
        return new ResponseEntity<>(watchLocationDTO, HttpStatus.OK);
    }

}
