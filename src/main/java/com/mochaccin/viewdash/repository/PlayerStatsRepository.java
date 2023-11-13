package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.PlayerStats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerStatsRepository extends MongoRepository<PlayerStats, ObjectId> {
    Optional<PlayerStats> findByPlayerId(ObjectId objectId);
    void deleteByPlayerId(ObjectId objectId);
}
