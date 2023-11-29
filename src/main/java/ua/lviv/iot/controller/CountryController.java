package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.CountryDTO;
import ua.lviv.iot.dto.RegionDTO;
import ua.lviv.iot.dto.assembler.CountryDTOAssembler;
import ua.lviv.iot.dto.assembler.RegionDTOAssembler;
import ua.lviv.iot.model.Country;
import ua.lviv.iot.model.Region;
import ua.lviv.iot.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private CountryService countryService;
    private CountryDTOAssembler countryDTOAssembler;
    private RegionDTOAssembler regionDTOAssembler;

    @Autowired
    public CountryController(CountryService countryService, CountryDTOAssembler countryDTOAssembler,
                             RegionDTOAssembler regionDTOAssembler) {
        this.countryService = countryService;
        this.countryDTOAssembler = countryDTOAssembler;
        this.regionDTOAssembler = regionDTOAssembler;

    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<CountryDTO>> findAll() {
        List<Country> countries = countryService.findAll();
        CollectionModel<CountryDTO> cityDTOs = countryDTOAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(cityDTOs, HttpStatus.OK);
    }

    @GetMapping("/{countryName}")
    public ResponseEntity<CountryDTO> findById(@PathVariable("countryName") String coutryName) {
        Country country = countryService.findById(coutryName);
        CountryDTO countryDTO = countryDTOAssembler.toModel(country);
        return new ResponseEntity<CountryDTO>(countryDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<RegionDTO>> findAllRegionsByCountryName(
            @RequestParam(name = "countryName", required = true) String countryName) {
        List<Region> regions = countryService.findAllRegionsByCountryName(countryName);
        CollectionModel<RegionDTO> regionDTOs = regionDTOAssembler.toCollectionModel(regions);
        return new ResponseEntity<CollectionModel<RegionDTO>>(regionDTOs, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<CountryDTO> addCountry(@RequestBody Country country) {
        Country country2 = countryService.save(country);
        CountryDTO countryDTO = countryDTOAssembler.toModel(country2);
        return new ResponseEntity<CountryDTO>(countryDTO, HttpStatus.OK);
    }

    @PutMapping("/{coutryName}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable("coutryName") String coutryName,
                                                    @RequestBody Country country) {
        Country country2 = countryService.update(country, coutryName);
        CountryDTO countryDTO = countryDTOAssembler.toModel(country2);
        return new ResponseEntity<CountryDTO>(countryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{coutryName}")
    public ResponseEntity<CountryDTO> deleteCountry(@PathVariable("coutryName") String coutryName) {

        Country country2 = countryService.deleteById(coutryName);
        CountryDTO countryDTO = countryDTOAssembler.toModel(country2);
        return new ResponseEntity<CountryDTO>(countryDTO, HttpStatus.OK);
    }

}
