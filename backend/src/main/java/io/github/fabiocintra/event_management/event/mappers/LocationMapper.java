package io.github.fabiocintra.event_management.event.mappers;

import io.github.fabiocintra.event_management.event.model.Location;
import io.github.fabiocintra.event_management.event.model.dto.location.LocationRequest;
import io.github.fabiocintra.event_management.utils.annotations.Mapper;

@Mapper
public class LocationMapper {

    public Location toEntity(LocationRequest request) {
        return new Location(
                request.country(),
                request.city(),
                request.street(),
                request.complement(),
                request.number()
        );
    }

}
