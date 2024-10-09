package com.eventspace.spring.spaceservice.mapper;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.dto.SpaceResponse;
import com.eventspace.spring.spaceservice.model.entity.Space;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SpaceMapper {
    Space toEntity(SpaceRequest spaceRequest);
    Space toEntity(SpaceResponse spaceResponse);

    SpaceResponse toSpaceResponse(Space space);
    SpaceRequest toSpaceRequest(Space space);

    List<SpaceResponse> toSpaceResponseList(List<Space> spaces);
    List<SpaceRequest> toSpaceRequestList(List<Space> spaces);
}
