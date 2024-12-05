package com.eventspace.spring.spaceservice.service;


import com.eventspace.spring.spaceservice.model.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    Comment updateComment(Long id, Comment comment);
    void deleteComment(Long id);
    Comment getCommentById(Long id);
    List<Comment> getAllComments();
    List<Comment> getCommentsBySpaceId(Long spaceId);
}

