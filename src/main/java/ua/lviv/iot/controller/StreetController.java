package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.StreetDTO;
import ua.lviv.iot.dto.assembler.StreetDTOAssembler;
import ua.lviv.iot.model.Street;
import ua.lviv.iot.service.StreetService;

import java.util.List;

@RestController
@RequestMapping("/api/street")
public class StreetController {

    private StreetService streetService;
    private StreetDTOAssembler streetDTOAssembler;

    @Autowired
    public StreetController(StreetService streetService, StreetDTOAssembler streetDTOAssembler) {
        this.streetService = streetService;
        this.streetDTOAssembler = streetDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<StreetDTO>> findAll() {
        List<Street> streets = streetService.findAll();
        CollectionModel<StreetDTO> collectionModel = streetDTOAssembler.toCollectionModel(streets);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{streetName}")
    public ResponseEntity<StreetDTO> findById(@PathVariable("streetName") String streetName) {
        Street street = streetService.findById(streetName);
        StreetDTO dto = streetDTOAssembler.toModel(street);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<StreetDTO> addStreet(@RequestBody Street street) {
        Street street2 = streetService.save(street);
        StreetDTO dto = streetDTOAssembler.toModel(street2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{streetName}")
    public ResponseEntity<StreetDTO> updateStreet(@PathVariable("streetName") String streetName,
                                                  @RequestBody Street street) {
        Street street2 = streetService.update(street, streetName);
        StreetDTO dto = streetDTOAssembler.toModel(street2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{streetName}")
    public ResponseEntity<StreetDTO> deleteStreet(@PathVariable("streetName") String streetName) {
        Street street2 = streetService.deleteById(streetName);
        StreetDTO dto = streetDTOAssembler.toModel(street2);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

}
