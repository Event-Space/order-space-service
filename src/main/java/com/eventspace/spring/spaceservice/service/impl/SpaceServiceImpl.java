package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.mapper.SpaceMapper;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.repository.SpaceRepository;
import com.eventspace.spring.spaceservice.service.SpaceService;
import com.eventspace.spring.spaceservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
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
        System.out.println("Received SpaceRequest: " + spaceRequest);

        Space space = spaceMapper.spaceRequestToSpace(spaceRequest);

        if (space == null) {
            System.out.println("Space mapping returned null.");
            return null;
        }

        String fileName = storageService.store(file);
        space.setImageUrl(fileName);

        return spaceRepository.save(space);
    }



    @Override
    public Space updateSpace(Long id, SpaceRequest spaceRequest, MultipartFile file) {
        Optional<Space> existingSpace = spaceRepository.findById(id);
        System.out.println("Received SpaceRequest: " + spaceRequest);

        if (spaceRequest.getName() != null) {
            existingSpace.get().setName(spaceRequest.getName());
        }
        if (spaceRequest.getAddress() != null) {
            existingSpace.get().setAddress(spaceRequest.getAddress());
        }
        if (spaceRequest.getLocation() != null) {
            existingSpace.get().setLocation(spaceRequest.getLocation());
        }
        if (spaceRequest.getSize() != null) {
            existingSpace.get().setSize(spaceRequest.getSize());
        }
        if (spaceRequest.getMaxCapacity() != null) {
            existingSpace.get().setMaxCapacity(spaceRequest.getMaxCapacity());
        }
        if (spaceRequest.getBaseRentalCost() != null) {
            existingSpace.get().setBaseRentalCost(spaceRequest.getBaseRentalCost());
        }
        if(file!=null){
            String fileName = storageService.store(file);
            existingSpace.get().setImageUrl(fileName);
        }
        return spaceRepository.save(existingSpace.get());
    }

    @Override
    public Space updateSpace(Long id, SpaceRequest spaceRequest) throws Exception {
        Space existingSpace = spaceRepository.findById(id)
                .orElseThrow(() -> new Exception("Space not found with ID: " + id));

        if (spaceRequest.getName() != null) {
            existingSpace.setName(spaceRequest.getName());
        }
        if (spaceRequest.getAddress() != null) {
            existingSpace.setAddress(spaceRequest.getAddress());
        }
        if (spaceRequest.getLocation() != null) {
            existingSpace.setLocation(spaceRequest.getLocation());
        }
        if (spaceRequest.getSize() != null) {
            existingSpace.setSize(spaceRequest.getSize());
        }
        if (spaceRequest.getMaxCapacity() != null) {
            existingSpace.setMaxCapacity(spaceRequest.getMaxCapacity());
        }
        if (spaceRequest.getBaseRentalCost() != null) {
            existingSpace.setBaseRentalCost(spaceRequest.getBaseRentalCost());
        }


        return spaceRepository.save(existingSpace);
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
