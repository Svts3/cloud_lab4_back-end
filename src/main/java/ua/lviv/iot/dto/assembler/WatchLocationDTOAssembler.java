package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.WatchLocationController;
import ua.lviv.iot.dto.WatchLocationDTO;
import ua.lviv.iot.model.WatchLocation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WatchLocationDTOAssembler
        implements RepresentationModelAssembler<WatchLocation, WatchLocationDTO> {

    @Override
    public WatchLocationDTO toModel(WatchLocation entity) {
        WatchLocationDTO dto = WatchLocationDTO.builder().id(entity.getId())
                .latitude(entity.getLatitude()).longitude(entity.getLongitude())
                .watchSerialNumber(entity.getWatch().getSerialNumber()).build();
        Link selfLink = linkTo(methodOn(WatchLocationController.class).findById(entity.getId()))
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    @Override
    public CollectionModel<WatchLocationDTO> toCollectionModel(
            Iterable<? extends WatchLocation> entities) {
        CollectionModel<WatchLocationDTO> watchLocationDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(WatchLocationController.class).findAll()).withSelfRel();
        watchLocationDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<WatchLocationDTO> toCollectionModel(
            Iterable<? extends WatchLocation> entities, Link link) {
        CollectionModel<WatchLocationDTO> watchLocationDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        watchLocationDTOs.add(link);
        return watchLocationDTOs;
    }

}
