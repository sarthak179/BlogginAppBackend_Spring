package com.example.BloggingAppAPI.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private CommentsRepository commentsRepository;
    public CommentsService(@Autowired CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}
