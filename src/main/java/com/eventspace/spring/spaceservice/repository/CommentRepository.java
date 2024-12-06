package com.eventspace.spring.spaceservice.repository;

import com.eventspace.spring.spaceservice.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBySpaceId(Long spaceId);
    List<Comment> findByUserEmail(String userEmail);

}
