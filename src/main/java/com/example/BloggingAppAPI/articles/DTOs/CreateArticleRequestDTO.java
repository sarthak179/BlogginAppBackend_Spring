package com.example.BloggingAppAPI.articles.DTOs;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequestDTO {
    @NonNull
    private String title;
    @NonNull
    private String body;

}
