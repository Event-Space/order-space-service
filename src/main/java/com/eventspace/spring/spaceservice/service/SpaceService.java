package com.eventspace.spring.spaceservice.service;


import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.dto.SpaceResponse;
import com.eventspace.spring.spaceservice.model.entity.Space;

import java.util.List;

public interface SpaceService {
    Space addSpace(SpaceRequest spaceRequest);
    Space updateSpace(Long id, SpaceRequest spaceRequest);
    void deleteSpace(Long id);
    Space getSpace(Long id);
    List<Space> getAllSpace();

}
