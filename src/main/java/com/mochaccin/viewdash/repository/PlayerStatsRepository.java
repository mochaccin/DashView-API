package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.PlayerStats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatsRepository extends MongoRepository<PlayerStats, ObjectId> {
}
