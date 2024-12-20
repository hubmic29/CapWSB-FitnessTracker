package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;
import java.util.Optional;

public interface StatisticsProvider {
    /**
     * Retrieves a statistics based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param statisticsId id of the statistics to be searched
     * @return An {@link Optional} containing the located Statistics, or {@link Optional#empty()} if not found
     */
    Optional<Statistics> getStatistics(Long statisticsId);

    /**
     * Retrieves all statistics.
     *
     * @return a list of all statistics
     */
    List<Statistics> getAllStatistics();

    /**
     * Retrieves statistics for a specific user.
     *
     * @param userId the ID of the user to search for statistics
     * @return statistics for the given user
     */
    Optional<Statistics> getStatisticsForUser(Long userId);

    /**
     * Retrieves a list of statistics with more burned calories than the given value.
     *
     * @param burnedCalories the burned calories to search for
     * @return a list of statistics with more burned calories than the given value
     */
    List<Statistics> getMoreCaloriesBurned(Long burnedCalories);
}