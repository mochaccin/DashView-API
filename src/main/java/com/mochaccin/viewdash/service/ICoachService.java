package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.Coach;

/**
 * Service interface for managing Coach objects.
 */
public interface ICoachService {

    /**
     * Create a new coach.
     *
     * @param coachBody The Coach object representing the new coach to be created.
     * @return true if the coach is successfully created, false otherwise.
     */
    boolean createCoach(Coach coachBody);

    /**
     * Delete a coach by their ID.
     *
     * @param coachId The ID of the coach to be deleted.
     * @return true if the coach is successfully deleted, false otherwise.
     */
    boolean deleteCoach(String coachId);
}
