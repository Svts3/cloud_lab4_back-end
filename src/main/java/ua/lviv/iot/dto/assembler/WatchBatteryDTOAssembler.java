package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.WatchBatteryController;
import ua.lviv.iot.dto.WatchBatteryDTO;
import ua.lviv.iot.model.WatchBattery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WatchBatteryDTOAssembler
        implements RepresentationModelAssembler<WatchBattery, WatchBatteryDTO> {

    @Override
    public WatchBatteryDTO toModel(WatchBattery entity) {
        WatchBatteryDTO dto = WatchBatteryDTO.builder().id(entity.getId())
                .chargeLevel(entity.getChargeLevel())
                .watchSerialNumber(entity.getWatch().getSerialNumber()).build();
        Link selfLink = linkTo(methodOn(WatchBatteryController.class).findById(entity.getId()))
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    @Override
    public CollectionModel<WatchBatteryDTO> toCollectionModel(
            Iterable<? extends WatchBattery> entities) {
        CollectionModel<WatchBatteryDTO> watchBatteryDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(WatchBatteryController.class).findAll()).withSelfRel();
        watchBatteryDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<WatchBatteryDTO> toCollectionModel(
            Iterable<? extends WatchBattery> entities, Link link) {
        CollectionModel<WatchBatteryDTO> watchBatteryDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        watchBatteryDTOs.add(link);
        return watchBatteryDTOs;
    }

}
