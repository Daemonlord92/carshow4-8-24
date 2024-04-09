package com.blitmatthew.carshow484.exception;

import com.blitmatthew.carshow484.entity.Car;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime timestamp
) {
}
