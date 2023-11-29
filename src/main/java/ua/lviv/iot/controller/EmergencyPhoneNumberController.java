package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.EmergencyPhoneNumberDTO;
import ua.lviv.iot.dto.assembler.EmergencyPhoneNumberDTOAssembler;
import ua.lviv.iot.model.EmergencyPhoneNumber;
import ua.lviv.iot.service.EmergencyPhoneNumberService;

import java.util.List;

@RestController
@RequestMapping("/api/emergencyPhoneNumber")
public class EmergencyPhoneNumberController {

    private EmergencyPhoneNumberService phoneNumberService;
    private EmergencyPhoneNumberDTOAssembler emergencyPhoneNumberDTOAssembler;

    @Autowired
    public EmergencyPhoneNumberController(EmergencyPhoneNumberService phoneNumberService,
                                          EmergencyPhoneNumberDTOAssembler emergencyPhoneNumberDTOAssembler) {
        this.phoneNumberService = phoneNumberService;
        this.emergencyPhoneNumberDTOAssembler = emergencyPhoneNumberDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EmergencyPhoneNumberDTO>> findAll() {
        List<EmergencyPhoneNumber> emergencyPhoneNumbers = phoneNumberService.findAll();
        CollectionModel<EmergencyPhoneNumberDTO> phoneNumberDTOs = emergencyPhoneNumberDTOAssembler
                .toCollectionModel(emergencyPhoneNumbers);
        return new ResponseEntity<CollectionModel<EmergencyPhoneNumberDTO>>(phoneNumberDTOs,
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyPhoneNumberDTO> findById(@PathVariable("id") Long id) {
        EmergencyPhoneNumber emergencyPhoneNumber = phoneNumberService.findById(id);
        EmergencyPhoneNumberDTO dto = emergencyPhoneNumberDTOAssembler
                .toModel(emergencyPhoneNumber);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EmergencyPhoneNumberDTO> addEmergencyPhoneNumber(
            @RequestBody EmergencyPhoneNumber emergencyPhoneNumber) {

        EmergencyPhoneNumber emergencyPhoneNumber2 = phoneNumberService.save(emergencyPhoneNumber);
        EmergencyPhoneNumberDTO dto = emergencyPhoneNumberDTOAssembler
                .toModel(emergencyPhoneNumber2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmergencyPhoneNumberDTO> updatePhoneNumber(@PathVariable("id") Long id,
                                                                     @RequestBody EmergencyPhoneNumber emergencyPhoneNumber) {

        EmergencyPhoneNumber emergencyPhoneNumber2 = phoneNumberService.update(emergencyPhoneNumber,
                id);
        EmergencyPhoneNumberDTO dto = emergencyPhoneNumberDTOAssembler
                .toModel(emergencyPhoneNumber2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmergencyPhoneNumberDTO> deletePhoneNumber(@PathVariable("id") Long id) {
        EmergencyPhoneNumber emergencyPhoneNumber2 = phoneNumberService.deleteById(id);
        EmergencyPhoneNumberDTO dto = emergencyPhoneNumberDTOAssembler
                .toModel(emergencyPhoneNumber2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
