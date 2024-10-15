package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.mapper.SpaceMapper;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.repository.SpaceRepository;
import com.eventspace.spring.spaceservice.service.SpaceService;
import com.eventspace.spring.spaceservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {
    private final SpaceRepository spaceRepository;
    private final SpaceMapper spaceMapper;
    private final StorageService storageService;

    @Override
    public Space addSpace(SpaceRequest spaceRequest, MultipartFile file) {
        Space space = spaceMapper.spaceRequestToSpace(spaceRequest);

        String fileName = storageService.store(file);

        space.setImageUrl(fileName);

        return spaceRepository.save(space);
    }


    @Override
    public Space updateSpace(Long id, SpaceRequest spaceRequest) {
        Space oldSpace = spaceRepository.findById(id).orElse(null);
        assert oldSpace != null;
        oldSpace.setName(spaceRequest.getName());
        oldSpace.setAddress(spaceRequest.getAddress());
        oldSpace.setLocation(spaceRequest.getLocation());
        oldSpace.setSize(spaceRequest.getSize());
        oldSpace.setMaxCapacity(spaceRequest.getMaxCapacity());
        spaceRepository.save(oldSpace);
        return oldSpace;
    }

    @Override
    public void deleteSpace(Long id) {
        spaceRepository.deleteById(id);
    }

    @Override
    public Space getSpace(Long id) {
        Optional<Space> space = spaceRepository.findById(id);
        return space.orElse(null);
    }

    @Override
    public List<Space> getAllSpace() {
        return spaceRepository.findAll();

    }
}
