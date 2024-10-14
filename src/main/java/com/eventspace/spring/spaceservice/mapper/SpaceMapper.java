package com.eventspace.spring.spaceservice.mapper;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.dto.SpaceResponse;
import com.eventspace.spring.spaceservice.model.entity.Space;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpaceMapper {
    SpaceMapper INSTANCE = Mappers.getMapper(SpaceMapper.class);
    SpaceResponse spaceToSpaceResponse(Space space);
    SpaceRequest spaceToSpaceRequest(Space space);
    Space spaceRequestToSpace(SpaceRequest spaceRequest);
    Space spaceResponseToSpace(SpaceResponse spaceResponse);
    List<SpaceResponse> spaceListToSpaceResponseList(List<Space> spaces);
    List<SpaceRequest> spaceListToSpaceRequestList(List<Space> spaces);
    List<Space> spaceRequestListToSpaceList(List<SpaceRequest> spaceRequests);
    List<Space> spaceResponseListToSpaceList(List<SpaceResponse> spaceResponses);
//lol
}
