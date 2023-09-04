package com.example.BloggingAppAPI.Commons.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String details;
}
