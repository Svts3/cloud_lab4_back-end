package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.CityDTO;
import ua.lviv.iot.dto.StreetDTO;
import ua.lviv.iot.dto.assembler.CityDTOAssembler;
import ua.lviv.iot.dto.assembler.StreetDTOAssembler;
import ua.lviv.iot.exception.RoleNotFoundException;
import ua.lviv.iot.model.City;
import ua.lviv.iot.model.Street;
import ua.lviv.iot.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private CityService cityService;
    private CityDTOAssembler cityDTOAssembler;
    private StreetDTOAssembler streetDTOAssembler;

    @Autowired
    public CityController(CityService cityService, CityDTOAssembler cityDTOAssembler,
                          StreetDTOAssembler streetDTOAssembler) {
        this.cityService = cityService;
        this.cityDTOAssembler = cityDTOAssembler;
        this.streetDTOAssembler = streetDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<CityDTO>> findAll() {
        List<City> cities = cityService.findAll();
        CollectionModel<CityDTO> cityDTOs = cityDTOAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(cityDTOs, HttpStatus.OK);
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<CityDTO> findByCityName(@PathVariable("cityName") String cityName) {
        City city = cityService.findById(cityName);
        CityDTO cityDTO = cityDTOAssembler.toModel(city);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @GetMapping("/{cityName}/streets")
    public ResponseEntity<CollectionModel<StreetDTO>> findAllStreetsByCityName(
            @PathVariable("cityName") String cityName) {
        List<Street> cities = cityService.findAllStreetsByCityName(cityName);
        CollectionModel<StreetDTO> streetDTOs = streetDTOAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(streetDTOs, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<CityDTO> addCity(@RequestBody City city) {
        City city2 = cityService.save(city);
        CityDTO cityDTO = cityDTOAssembler.toModel(city2);
        return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
    }

    @PutMapping("/{cityName}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable("cityName") String cityName,
                                              @RequestBody City city) {
        City city2 = cityService.update(city, cityName);
        CityDTO cityDTO = cityDTOAssembler.toModel(city2);
        return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{cityName}")
    public ResponseEntity<CityDTO> deleteCity(@PathVariable("cityName") String cityName) {
        City city2 = cityService.deleteById(cityName);
        CityDTO cityDTO = cityDTOAssembler.toModel(city2);
        return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
    }

}
