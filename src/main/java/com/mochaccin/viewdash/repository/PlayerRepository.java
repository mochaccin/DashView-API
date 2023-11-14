package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Player entities.
 * Extends MongoRepository, providing CRUD operations and additional query methods for Player entities.
 */
@Repository
public interface PlayerRepository extends MongoRepository<Player, ObjectId> {

    /**
     * Finds a Player by their nickname.
     *
     * @param nickname The nickname of the Player to be searched for.
     * @return An Optional containing the Player if found, or an empty Optional if not found.
     */
    Optional<Player> findPlayerByNickname(String nickname);

    /**
     * Checks if a Player with the given nickname exists.
     *
     * @param nickname The nickname of the Player to check for existence.
     * @return true if a Player with the given nickname exists, false otherwise.
     */
    boolean existsByNickname(String nickname);
}
