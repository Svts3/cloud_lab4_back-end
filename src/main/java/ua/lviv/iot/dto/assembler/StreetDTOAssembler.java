package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.StreetController;
import ua.lviv.iot.dto.StreetDTO;
import ua.lviv.iot.model.Street;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StreetDTOAssembler implements RepresentationModelAssembler<Street, StreetDTO> {

    @Override
    public StreetDTO toModel(Street entity) {
        StreetDTO dto = StreetDTO.builder().name(entity.getName())
                .cityName(entity.getCity().getName()).build();
        Link selfLink = linkTo(methodOn(StreetController.class).findById(entity.getName()))
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    @Override
    public CollectionModel<StreetDTO> toCollectionModel(Iterable<? extends Street> entities) {
        CollectionModel<StreetDTO> cityDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(StreetController.class).findAll()).withSelfRel();
        cityDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<StreetDTO> toCollectionModel(Iterable<? extends Street> entities,
                                                        Link link) {
        CollectionModel<StreetDTO> cityDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        cityDTOs.add(link);
        return cityDTOs;
    }

}
