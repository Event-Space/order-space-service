package com.eventspace.spring.spaceservice.service.impl;


import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.mapper.SpaceMapper;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.repository.SpaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SpaceServiceImplTest {

    @Mock
    private SpaceRepository spaceRepository;

    @Mock
    private SpaceMapper spaceMapper;

    @InjectMocks
    private SpaceServiceImpl spaceServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSpace_ShouldReturnAddedSpace() {
        SpaceRequest spaceRequest = new SpaceRequest();
        spaceRequest.setName("Conference Room A");

        Space spaceEntity = new Space();
        spaceEntity.setName("Conference Room A");

        when(spaceMapper.toEntity(any(SpaceRequest.class))).thenReturn(spaceEntity);
        when(spaceRepository.save(any(Space.class))).thenReturn(spaceEntity);

        Space result = spaceServiceImpl.addSpace(spaceRequest);

        assertNotNull(result);
        assertEquals("Conference Room A", result.getName());
        verify(spaceRepository, times(1)).save(spaceEntity);
    }

    @Test
    void updateSpace_ShouldReturnUpdatedSpace() {
        Long spaceId = 1L;
        SpaceRequest spaceRequest = new SpaceRequest();
        spaceRequest.setName("Updated Space");

        Space oldSpace = new Space();
        oldSpace.setName("Old Space");

        when(spaceRepository.findById(spaceId)).thenReturn(Optional.of(oldSpace));
        when(spaceRepository.save(any(Space.class))).thenReturn(oldSpace);

        Space updatedSpace = spaceServiceImpl.updateSpace(spaceId, spaceRequest);

        assertNotNull(updatedSpace);
        assertEquals("Updated Space", updatedSpace.getName());
        verify(spaceRepository, times(1)).findById(spaceId);
        verify(spaceRepository, times(1)).save(oldSpace);
    }

    @Test
    void deleteSpace_ShouldInvokeDelete() {
        Long spaceId = 1L;

        doNothing().when(spaceRepository).deleteById(spaceId);

        spaceServiceImpl.deleteSpace(spaceId);

        verify(spaceRepository, times(1)).deleteById(spaceId);
    }

    @Test
    void getSpace_ShouldReturnSpaceById() {
        Long spaceId = 1L;
        Space space = new Space();
        space.setId(spaceId);

        when(spaceRepository.findById(spaceId)).thenReturn(Optional.of(space));

        Space result = spaceServiceImpl.getSpace(spaceId);

        assertNotNull(result);
        assertEquals(spaceId, result.getId());
        verify(spaceRepository, times(1)).findById(spaceId);
    }

    @Test
    void getAllSpace_ShouldReturnListOfSpaces() {
        Space space1 = new Space();
        space1.setName("Space 1");
        Space space2 = new Space();
        space2.setName("Space 2");

        when(spaceRepository.findAll()).thenReturn(Arrays.asList(space1, space2));

        List<Space> spaces = spaceServiceImpl.getAllSpace();

        assertEquals(2, spaces.size());
        verify(spaceRepository, times(1)).findAll();
    }

    @Test
    void getSpace_ShouldReturnNullIfNotFound() {
        Long spaceId = 1L;

        when(spaceRepository.findById(spaceId)).thenReturn(Optional.empty());

        Space result = spaceServiceImpl.getSpace(spaceId);

        assertNull(result);
        verify(spaceRepository, times(1)).findById(spaceId);
    }
}
