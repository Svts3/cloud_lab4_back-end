package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.PropertyInfoDTO;
import ua.lviv.iot.dto.assembler.PropertyInfoDTOAssembler;
import ua.lviv.iot.model.PropertyInfo;
import ua.lviv.iot.service.PropertyInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/propertyInfo")
public class PropertyInfoController {

    private PropertyInfoService propertyInfoService;
    private PropertyInfoDTOAssembler propertyInfoDTOAssembler;

    @Autowired
    public PropertyInfoController(PropertyInfoService propertyInfoService,
                                  PropertyInfoDTOAssembler propertyInfoDTOAssembler) {
        this.propertyInfoService = propertyInfoService;
        this.propertyInfoDTOAssembler = propertyInfoDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<PropertyInfoDTO>> findAll() {
        List<PropertyInfo> propertyInfos = propertyInfoService.findAll();
        CollectionModel<PropertyInfoDTO> collectionModel = propertyInfoDTOAssembler
                .toCollectionModel(propertyInfos);
        return new ResponseEntity<CollectionModel<PropertyInfoDTO>>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyInfoDTO> findById(@PathVariable("id") Long id) {
        PropertyInfo info = propertyInfoService.findById(id);
        PropertyInfoDTO dto = propertyInfoDTOAssembler.toModel(info);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PropertyInfoDTO> addPropertyInfo(@RequestBody PropertyInfo info) {
        PropertyInfo info2 = propertyInfoService.save(info);
        PropertyInfoDTO dto = propertyInfoDTOAssembler.toModel(info2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/storedProcedure")
    public ResponseEntity<PropertyInfoDTO> insertPropertyInfo(@RequestBody PropertyInfo info) {
        propertyInfoService.propertyInfoInsetion(info.getOwner().getId(),
                info.getWatch().getSerialNumber());
        PropertyInfoDTO propertyInfoDTO = propertyInfoDTOAssembler.toModel(info);
        return new ResponseEntity<>(propertyInfoDTO, HttpStatus.OK);
    }

    @PutMapping("/{propertyInfoId}")
    public ResponseEntity<PropertyInfoDTO> updatePropertyInfo(
            @PathVariable("propertyInfoId") Long propertyInfoId,
            @RequestBody PropertyInfo propertyInfo) {
        PropertyInfo info2 = propertyInfoService.update(propertyInfo, propertyInfoId);
        PropertyInfoDTO dto = propertyInfoDTOAssembler.toModel(info2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{propertyInfoId}")
    public ResponseEntity<PropertyInfoDTO> deletePropertyInfo(
            @PathVariable("propertyInfoId") Long propertyInfoId) {
        PropertyInfo info2 = propertyInfoService.deleteById(propertyInfoId);
        PropertyInfoDTO dto = propertyInfoDTOAssembler.toModel(info2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
