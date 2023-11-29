package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.RegionController;
import ua.lviv.iot.dto.RegionDTO;
import ua.lviv.iot.model.Region;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionDTOAssembler implements RepresentationModelAssembler<Region, RegionDTO> {

    @Override
    public RegionDTO toModel(Region entity) {
        RegionDTO dto = RegionDTO.builder().name(entity.getName())
                .countryName(entity.getCountry().getName()).build();
        Link selfLink = linkTo(methodOn(RegionController.class).findById(entity.getName()))
                .withSelfRel();
        Link cityLink = linkTo(
                methodOn(RegionController.class).findAllCitiesByRegionName(entity.getName()))
                .withRel("cities");
        dto.add(selfLink);
        dto.add(cityLink);
        return dto;
    }

    @Override
    public CollectionModel<RegionDTO> toCollectionModel(Iterable<? extends Region> entities) {
        CollectionModel<RegionDTO> regionDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(RegionController.class).findAll()).withSelfRel();
        regionDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<RegionDTO> toCollectionModel(Iterable<? extends Region> entities,
                                                        Link link) {
        CollectionModel<RegionDTO> regionDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        regionDTOs.add(link);
        return regionDTOs;
    }

}
