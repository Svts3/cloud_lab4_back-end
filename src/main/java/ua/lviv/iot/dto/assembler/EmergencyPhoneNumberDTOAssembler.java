package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.EmergencyPhoneNumberController;
import ua.lviv.iot.controller.HealthInfoController;
import ua.lviv.iot.dto.EmergencyPhoneNumberDTO;
import ua.lviv.iot.model.EmergencyPhoneNumber;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmergencyPhoneNumberDTOAssembler
        implements RepresentationModelAssembler<EmergencyPhoneNumber, EmergencyPhoneNumberDTO> {

    @Override
    public EmergencyPhoneNumberDTO toModel(EmergencyPhoneNumber entity) {
        EmergencyPhoneNumberDTO dto = EmergencyPhoneNumberDTO.builder().id(entity.getId())
                .phoneNumber(entity.getPhoneNumber()).watchSerialNumber(entity.getWatch().getSerialNumber()).build();
        Link selfLink = linkTo(methodOn(EmergencyPhoneNumberController.class).findById(entity.getId()))
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    @Override
    public CollectionModel<EmergencyPhoneNumberDTO> toCollectionModel(Iterable<? extends EmergencyPhoneNumber> entities) {
        CollectionModel<EmergencyPhoneNumberDTO> emergencyPhoneNumberDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(HealthInfoController.class).findAll()).withSelfRel();
        emergencyPhoneNumberDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<EmergencyPhoneNumberDTO> toCollectionModel(Iterable<? extends EmergencyPhoneNumber> entities,
                                                                      Link link) {
        CollectionModel<EmergencyPhoneNumberDTO> emergencyPhoneNumberDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        emergencyPhoneNumberDTOs.add(link);
        return emergencyPhoneNumberDTOs;
    }

}
