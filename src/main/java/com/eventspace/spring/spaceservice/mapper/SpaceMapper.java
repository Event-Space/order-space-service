package com.eventspace.spring.spaceservice.mapper;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.dto.SpaceResponse;
import com.eventspace.spring.spaceservice.model.entity.Space;
import org.springframework.stereotype.Component;

@Component
public class SpaceMapper {

    // Convert Space to SpaceResponse
    public SpaceResponse spaceToSpaceResponse(Space space) {
        if (space == null) {
            return null;
        }

        SpaceResponse response = new SpaceResponse();
        response.setId(space.getId());
        response.setAddress(space.getAddress());
        response.setLocation(space.getLocation());
        response.setSize(space.getSize());
        response.setMaxCapacity(space.getMaxCapacity());
        response.setBaseRentalCost(space.getBaseRentalCost());
        response.setAvailabilityStatus(space.getAvailabilityStatus());
        return response;
    }

    // Convert Space to SpaceRequest
    public SpaceRequest spaceToSpaceRequest(Space space) {
        if (space == null) {
            return null;
        }

        SpaceRequest request = new SpaceRequest();
        request.setName(space.getName());
        request.setAddress(space.getAddress());
        request.setLocation(space.getLocation());
        request.setSize(space.getSize());
        request.setMaxCapacity(space.getMaxCapacity());
        request.setBaseRentalCost(space.getBaseRentalCost());
        request.setAvailabilityStatus(space.getAvailabilityStatus());
        return request;
    }

    // Convert SpaceRequest to Space
    public Space spaceRequestToSpace(SpaceRequest spaceRequest) {
        if (spaceRequest == null) {
            return null;
        }

        Space space = new Space();
        space.setName(spaceRequest.getName());
        space.setAddress(spaceRequest.getAddress());
        space.setLocation(spaceRequest.getLocation());
        space.setSize(spaceRequest.getSize());
        space.setMaxCapacity(spaceRequest.getMaxCapacity());
        space.setBaseRentalCost(spaceRequest.getBaseRentalCost());
        space.setAvailabilityStatus(spaceRequest.getAvailabilityStatus());
        return space;
    }

    // Convert SpaceResponse to Space
    public Space spaceResponseToSpace(SpaceResponse spaceResponse) {
        if (spaceResponse == null) {
            return null;
        }

        Space space = new Space();
        space.setId(spaceResponse.getId()); // Assuming you want to set the ID in the entity
        space.setAddress(spaceResponse.getAddress());
        space.setLocation(spaceResponse.getLocation());
        space.setSize(spaceResponse.getSize());
        space.setMaxCapacity(spaceResponse.getMaxCapacity());
        space.setBaseRentalCost(spaceResponse.getBaseRentalCost());
        space.setAvailabilityStatus(spaceResponse.getAvailabilityStatus());
        return space;
    }
}
