package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.PlayerStats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing PlayerStats objects in MongoDB.
 */
@Repository
public interface PlayerStatsRepository extends MongoRepository<PlayerStats, ObjectId> {

    /**
     * Find a PlayerStats object by its player ID.
     *
     * @param objectId The player ID to search for.
     * @return An Optional containing the PlayerStats object if found, or an empty Optional if not found.
     */
    Optional<PlayerStats> findByPlayerId(ObjectId objectId);

    /**
     * Delete a PlayerStats object by its player ID.
     *
     * @param objectId The player ID of the PlayerStats object to be deleted.
     */
    void deleteByPlayerId(ObjectId objectId);
}
