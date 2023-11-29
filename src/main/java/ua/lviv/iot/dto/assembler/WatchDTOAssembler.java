package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.WatchController;
import ua.lviv.iot.dto.WatchDTO;
import ua.lviv.iot.model.Watch;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WatchDTOAssembler implements RepresentationModelAssembler<Watch, WatchDTO> {

    @Override
    public WatchDTO toModel(Watch entity) {
        WatchDTO dto = WatchDTO.builder().serialNumber(entity.getSerialNumber())
                .streetName(entity.getStreet().getName()).build();
        Link selfLink = linkTo(methodOn(WatchController.class).findById(entity.getSerialNumber()))
                .withSelfRel();
        Link ownerLink = linkTo(methodOn(WatchController.class)
                .findAllWatchOwnersBySerialNumber(entity.getSerialNumber())).withRel("owners");
        dto.add(selfLink);
        dto.add(ownerLink);
        return dto;
    }

    @Override
    public CollectionModel<WatchDTO> toCollectionModel(Iterable<? extends Watch> entities) {
        CollectionModel<WatchDTO> watchDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(WatchController.class).findAll()).withSelfRel();
        watchDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<WatchDTO> toCollectionModel(Iterable<? extends Watch> entities,
                                                       Link link) {
        CollectionModel<WatchDTO> watchDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        watchDTOs.add(link);
        return watchDTOs;
    }

}
