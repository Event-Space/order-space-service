package com.eventspace.spring.spaceservice.mapper;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.dto.SpaceResponse;
import com.eventspace.spring.spaceservice.model.entity.Space;
import org.springframework.stereotype.Component;

@Component
public class SpaceMapper {

    public SpaceResponse spaceToSpaceResponse(Space space) {
        if (space == null) {
            return null;
        }

        SpaceResponse response = new SpaceResponse();
        response.setId(space.getId());
        response.setName(space.getName());
        response.setAddress(space.getAddress());
        response.setLocation(space.getLocation());
        response.setSize(space.getSize());
        response.setMaxCapacity(space.getMaxCapacity());
        response.setBaseRentalCost(space.getBaseRentalCost());
        response.setImageUrl(space.getImageUrl());

        return response;
    }

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

        return request;
    }

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

        return space;
    }

    public Space spaceResponseToSpace(SpaceResponse spaceResponse) {
        if (spaceResponse == null) {
            return null;
        }

        Space space = new Space();
        space.setId(spaceResponse.getId());
        space.setName(spaceResponse.getName());
        space.setAddress(spaceResponse.getAddress());
        space.setLocation(spaceResponse.getLocation());
        space.setSize(spaceResponse.getSize());
        space.setMaxCapacity(spaceResponse.getMaxCapacity());
        space.setBaseRentalCost(spaceResponse.getBaseRentalCost());
        space.setImageUrl(spaceResponse.getImageUrl());

        return space;
    }
}
