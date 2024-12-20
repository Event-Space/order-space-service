package com.eventspace.spring.spaceservice.service;


import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.dto.SpaceResponse;
import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.model.entity.Space;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public interface SpaceService {
    Space addSpace(SpaceRequest spaceRequest, MultipartFile file);
    Space updateSpace(Long id, SpaceRequest spaceRequest, MultipartFile file) throws Exception;
    Space updateSpace(Long id, SpaceRequest spaceRequest) throws Exception;
    void deleteSpace(Long id);
    Space getSpace(Long id);
    List<Space> getAllSpace();
    List<Slot> getFreeTimes(Long spaceId, LocalDate date);

}
