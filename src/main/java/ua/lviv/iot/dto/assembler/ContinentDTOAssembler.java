package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.ContinentController;
import ua.lviv.iot.dto.ContinentDTO;
import ua.lviv.iot.model.Continent;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContinentDTOAssembler implements RepresentationModelAssembler<Continent, ContinentDTO> {

    @Override
    public ContinentDTO toModel(Continent entity) {
        ContinentDTO dto = ContinentDTO.builder().name(entity.getName()).build();
        Link selfLink = linkTo(methodOn(ContinentController.class).findById(entity.getName()))
                .withSelfRel();
        dto.add(selfLink);
        return dto;
    }

    @Override
    public CollectionModel<ContinentDTO> toCollectionModel(Iterable<? extends Continent> entities) {
        CollectionModel<ContinentDTO> countryDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        Link selfLink = linkTo(methodOn(ContinentController.class).findAll()).withSelfRel();
        countryDTOs.add(selfLink);
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<ContinentDTO> toCollectionModel(Iterable<? extends Continent> entities,
                                                           Link link) {
        CollectionModel<ContinentDTO> countryDTOs = RepresentationModelAssembler.super.toCollectionModel(
                entities);
        countryDTOs.add(link);
        return countryDTOs;
    }

}
