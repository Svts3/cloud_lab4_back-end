package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.CityDTO;
import ua.lviv.iot.dto.RegionDTO;
import ua.lviv.iot.dto.assembler.CityDTOAssembler;
import ua.lviv.iot.dto.assembler.RegionDTOAssembler;
import ua.lviv.iot.model.City;
import ua.lviv.iot.model.Region;
import ua.lviv.iot.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    private RegionService regionService;
    private RegionDTOAssembler regionDTOAssembler;
    private CityDTOAssembler cityDTOAssembler;

    @Autowired
    public RegionController(RegionService regionService, RegionDTOAssembler regionDTOAssembler,
                            CityDTOAssembler cityDTOAssembler) {
        this.regionService = regionService;
        this.regionDTOAssembler = regionDTOAssembler;
        this.cityDTOAssembler = cityDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<RegionDTO>> findAll() {
        List<Region> regionDTOs = regionService.findAll();
        CollectionModel<RegionDTO> collectionModel = regionDTOAssembler
                .toCollectionModel(regionDTOs);
        return new ResponseEntity<CollectionModel<RegionDTO>>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{regionName}")
    public ResponseEntity<RegionDTO> findById(@PathVariable("regionName") String regionName) {
        Region region = regionService.findById(regionName);
        RegionDTO dto = regionDTOAssembler.toModel(region);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<CityDTO>> findAllCitiesByRegionName(
            @RequestParam(name = "regionName", required = true) String regionName) {
        List<City> city = regionService.findAllCitiesByRegionName(regionName);
        CollectionModel<CityDTO> cityDTO = cityDTOAssembler.toCollectionModel(city);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<RegionDTO> addRegion(@RequestBody Region region) {
        Region region2 = regionService.save(region);
        RegionDTO dto = regionDTOAssembler.toModel(region2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{regionName}")
    public ResponseEntity<RegionDTO> updateRegion(@PathVariable("regionName") String regionName,
                                                  @RequestBody Region region) {
        Region region2 = regionService.update(region, regionName);
        RegionDTO dto = regionDTOAssembler.toModel(region2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{regionName}")
    public ResponseEntity<RegionDTO> deleteCountry(@PathVariable("regionName") String regionName) {
        Region region2 = regionService.deleteById(regionName);
        RegionDTO dto = regionDTOAssembler.toModel(region2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
