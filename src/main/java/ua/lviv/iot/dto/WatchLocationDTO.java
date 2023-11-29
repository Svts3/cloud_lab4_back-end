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
@Relation(itemRelation = "watchLocation", collectionRelation = "watchLocations")
public class WatchLocationDTO extends RepresentationModel<WatchLocationDTO> {

    private final Long id;
    private final Double latitude;
    private final Double longitude;
    private final String watchSerialNumber;

}
