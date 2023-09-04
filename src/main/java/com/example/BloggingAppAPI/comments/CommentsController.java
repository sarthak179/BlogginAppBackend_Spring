package com.example.BloggingAppAPI.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/{article-slug}/comments")
public class CommentsController {
    private CommentsService commentsService;
    public CommentsController(@Autowired CommentsService commentsService) {
        this.commentsService = commentsService;
    }
}
