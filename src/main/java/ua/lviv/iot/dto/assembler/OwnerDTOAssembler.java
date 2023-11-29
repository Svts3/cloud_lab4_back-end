package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.OwnerController;
import ua.lviv.iot.dto.OwnerDTO;
import ua.lviv.iot.model.Owner;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OwnerDTOAssembler implements RepresentationModelAssembler<Owner, OwnerDTO> {

    @Override
    public OwnerDTO toModel(Owner entity) {
        OwnerDTO dto = OwnerDTO.builder().id(entity.getId()).firstName(entity.getFirstName())
                .lastName(entity.getLastName()).dateOfBirth(entity.getDateOfBirth())
                .gender(entity.getGender()).build();
        Link selfLink = linkTo(methodOn(OwnerController.class).findById(entity.getId()))
                .withSelfRel();
        Link ownerLink = linkTo(
                methodOn(OwnerController.class).findAllWatchesByOwnerId(entity.getId()))
                .withRel("watches");
        dto.add(selfLink);
        dto.add(ownerLink);
        return dto;
    }

    @Override
    public CollectionModel<OwnerDTO> toCollectionModel(Iterable<? extends Owner> entities) {
        CollectionModel<OwnerDTO> ownerDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(OwnerController.class).findAll()).withSelfRel();
        ownerDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<OwnerDTO> toCollectionModel(Iterable<? extends Owner> entities,
                                                       Link link) {
        CollectionModel<OwnerDTO> ownerDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        ownerDTOs.add(link);
        return ownerDTOs;
    }

}
