package com.eventspace.spring.spaceservice.service.impl;

import com.eventspace.spring.spaceservice.model.entity.Comment;
import com.eventspace.spring.spaceservice.repository.CommentRepository;
import com.eventspace.spring.spaceservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        comment.setCreateTime(new java.util.Date());
        comment.setUpdateTime(new java.util.Date());
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment updatedComment = existingComment.get();
            updatedComment.setContent(comment.getContent());
            updatedComment.setUpdateTime(new java.util.Date());
            return commentRepository.save(updatedComment);
        } else {
            throw new RuntimeException("Comment not found");
        }
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsBySpaceId(Long spaceId) {
        return commentRepository.findBySpaceId(spaceId);
    }
}
