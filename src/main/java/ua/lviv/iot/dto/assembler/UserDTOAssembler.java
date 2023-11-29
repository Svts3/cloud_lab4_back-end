package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.OwnerController;
import ua.lviv.iot.controller.UserController;
import ua.lviv.iot.dto.UserDTO;
import ua.lviv.iot.model.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserDTOAssembler implements RepresentationModelAssembler<User, UserDTO> {

    @Override
    public UserDTO toModel(User entity) {
        UserDTO dto = UserDTO.builder().id(entity.getId()).firstName(entity.getFirstName())
                .lastName(entity.getLastName()).dateOfBirth(entity.getDateOfBirth())
                .gender(entity.getGender()).propertyInfoId(entity.getPropertyInfo().getId())
                .build();
        Link selfLink = linkTo(methodOn(UserController.class).findById(entity.getId()))
                .withSelfRel();
        Link ownerLink = linkTo(
                methodOn(OwnerController.class).findAllWatchesByOwnerId(entity.getId()))
                .withRel("users");
        dto.add(selfLink);
        dto.add(ownerLink);
        return dto;
    }

    @Override
    public CollectionModel<UserDTO> toCollectionModel(Iterable<? extends User> entities) {
        CollectionModel<UserDTO> userDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(UserController.class).findAll()).withSelfRel();
        userDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<UserDTO> toCollectionModel(Iterable<? extends User> entities,
                                                      Link link) {
        CollectionModel<UserDTO> userDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        userDTOs.add(link);
        return userDTOs;
    }

}
