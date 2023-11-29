package ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "owner", collectionRelation = "owners")
public class OwnerDTO extends RepresentationModel<OwnerDTO> {

    private final Long id;

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final LocalDate dateOfBirth;
    private final String gender;
}
