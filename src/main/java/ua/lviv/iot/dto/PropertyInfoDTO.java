package ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "propertyInfo", collectionRelation = "propertyInfos")
public class PropertyInfoDTO extends RepresentationModel<PropertyInfoDTO> {
    private Long id;
    private final Long ownerId;
    private final String watchSerialNumber;

}
