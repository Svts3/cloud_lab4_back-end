package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.HealthInfoController;
import ua.lviv.iot.dto.HealthInfoDTO;
import ua.lviv.iot.model.HealthInfo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HealthInfoDTOAssembler
        implements RepresentationModelAssembler<HealthInfo, HealthInfoDTO> {

    @Override
    public HealthInfoDTO toModel(HealthInfo entity) {
        HealthInfoDTO dto = HealthInfoDTO.builder().id(entity.getId())
                .heartbeatRate(entity.getHeartbeatRate())
                .watchSerialNumber(entity.getWatch().getSerialNumber()).build();
        Link selfLink = linkTo(methodOn(HealthInfoController.class).findById(entity.getId()))
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    @Override
    public CollectionModel<HealthInfoDTO> toCollectionModel(
            Iterable<? extends HealthInfo> entities) {
        CollectionModel<HealthInfoDTO> healthInfoDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(HealthInfoController.class).findAll()).withSelfRel();
        healthInfoDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<HealthInfoDTO> toCollectionModel(Iterable<? extends HealthInfo> entities,
                                                            Link link) {
        CollectionModel<HealthInfoDTO> healthInfoDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        healthInfoDTOs.add(link);
        return healthInfoDTOs;
    }

}
