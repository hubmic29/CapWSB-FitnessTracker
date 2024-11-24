package com.capgemini.wsb.fitnesstracker.statistics.internal;
import jakarta.annotation.Nullable;

public record StatisticsDtoWithUserId(
        @Nullable Long id,
        @Nullable Long userId,
        int totalTrainings,
        double totalDistance,
        int totalCaloriesBurned
){}