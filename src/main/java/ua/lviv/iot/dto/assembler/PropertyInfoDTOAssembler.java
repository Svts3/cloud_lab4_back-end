package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.PropertyInfoController;
import ua.lviv.iot.dto.PropertyInfoDTO;
import ua.lviv.iot.model.PropertyInfo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PropertyInfoDTOAssembler
        implements RepresentationModelAssembler<PropertyInfo, PropertyInfoDTO> {

    @Override
    public PropertyInfoDTO toModel(PropertyInfo entity) {
        PropertyInfoDTO dto = PropertyInfoDTO.builder().id(entity.getId())
                .ownerId(entity.getOwner().getId())
                .watchSerialNumber(entity.getWatch().getSerialNumber()).build();
        Link selfLink = linkTo(methodOn(PropertyInfoController.class).findById(entity.getId()))
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    @Override
    public CollectionModel<PropertyInfoDTO> toCollectionModel(
            Iterable<? extends PropertyInfo> entities) {
        CollectionModel<PropertyInfoDTO> propertyInfoDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(PropertyInfoController.class).findAll()).withSelfRel();
        propertyInfoDTOs.add(selfLink);
        return propertyInfoDTOs;
    }

    public CollectionModel<PropertyInfoDTO> toCollectionModel(
            Iterable<? extends PropertyInfo> entities, Link link) {
        CollectionModel<PropertyInfoDTO> ownerDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        ownerDTOs.add(link);
        return ownerDTOs;
    }

}
