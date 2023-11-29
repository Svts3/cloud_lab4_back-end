package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.CountryController;
import ua.lviv.iot.dto.CountryDTO;
import ua.lviv.iot.model.Country;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CountryDTOAssembler implements RepresentationModelAssembler<Country, CountryDTO> {

    @Override
    public CountryDTO toModel(Country entity) {
        CountryDTO dto = CountryDTO.builder().name(entity.getName())
                .continent(entity.getContinent_name()).build();
        Link selfLink = linkTo(methodOn(CountryController.class).findById(entity.getName()))
                .withSelfRel();
        Link regionLink = linkTo(
                methodOn(CountryController.class).findAllRegionsByCountryName(entity.getName()))
                .withRel("regions");
        dto.add(selfLink);
        dto.add(regionLink);
        return dto;
    }

    @Override
    public CollectionModel<CountryDTO> toCollectionModel(Iterable<? extends Country> entities) {
        CollectionModel<CountryDTO> countryDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(CountryController.class).findAll()).withSelfRel();
        countryDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<CountryDTO> toCollectionModel(Iterable<? extends Country> entities,
                                                         Link link) {
        CollectionModel<CountryDTO> countryDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        countryDTOs.add(link);
        return countryDTOs;
    }

}
