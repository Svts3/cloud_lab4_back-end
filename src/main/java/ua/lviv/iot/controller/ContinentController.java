package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.ContinentDTO;
import ua.lviv.iot.dto.assembler.ContinentDTOAssembler;
import ua.lviv.iot.model.Continent;
import ua.lviv.iot.service.ContinentService;

import java.util.List;

@RestController
@RequestMapping("/api/continent")
public class ContinentController {

    private ContinentService continentService;
    private ContinentDTOAssembler continentDTOAssembler;

    @Autowired
    public ContinentController(ContinentService continentService,
                               ContinentDTOAssembler continentDTOAssembler) {
        this.continentService = continentService;
        this.continentDTOAssembler = continentDTOAssembler;
    }

    @GetMapping("/{name}")
    public ResponseEntity<ContinentDTO> findById(@PathVariable("name") String name) {
        Continent continent = continentService.findById(name);
        ContinentDTO continentDTO = continentDTOAssembler.toModel(continent);
        return new ResponseEntity<ContinentDTO>(continentDTO, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<ContinentDTO>> findAll() {
        List<Continent> continents = this.continentService.findAll();
        CollectionModel<ContinentDTO> collectionModel = continentDTOAssembler
                .toCollectionModel(continents);

        return new ResponseEntity<CollectionModel<ContinentDTO>>(collectionModel, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ContinentDTO> addContinent(@RequestBody String name) {
        continentService.continentInsertion(name);
        ContinentDTO continentDTO = continentDTOAssembler.toModel(new Continent(name));

        return new ResponseEntity<ContinentDTO>(continentDTO, HttpStatus.OK);
    }

    @PutMapping("/{name}")
    public ResponseEntity<ContinentDTO> updateContinent(@PathVariable("name") String name,
                                                        @RequestBody Continent continent) {
        Continent continent2 = continentService.update(continent, name);
        ContinentDTO continentDTO = continentDTOAssembler.toModel(continent2);

        return new ResponseEntity<ContinentDTO>(continentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ContinentDTO> deleteContinent(@PathVariable("name") String name) {
        Continent continent2 = continentService.deleteById(name);
        ContinentDTO continentDTO = continentDTOAssembler.toModel(continent2);

        return new ResponseEntity<ContinentDTO>(continentDTO, HttpStatus.OK);
    }

}
