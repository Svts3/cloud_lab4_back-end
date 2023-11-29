package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.CityController;
import ua.lviv.iot.dto.CityDTO;
import ua.lviv.iot.model.City;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityDTOAssembler implements RepresentationModelAssembler<City, CityDTO> {

    @Override
    public CityDTO toModel(City entity) {
        CityDTO dto = CityDTO.builder().name(entity.getName())
                .regionName(entity.getRegion().getName()).build();
        Link selfLink = linkTo(methodOn(CityController.class).findByCityName(entity.getName()))
                .withSelfRel();
        Link regionLink = linkTo(
                methodOn(CityController.class).findAllStreetsByCityName(entity.getName()))
                .withRel("streets");
        dto.add(selfLink);
        dto.add(regionLink);
        return dto;
    }

    @Override
    public CollectionModel<CityDTO> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityDTO> cityDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(CityController.class).findAll()).withSelfRel();
        cityDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<CityDTO> toCollectionModel(Iterable<? extends City> entities,
                                                      Link link) {
        CollectionModel<CityDTO> cityDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        cityDTOs.add(link);
        return cityDTOs;
    }

}
