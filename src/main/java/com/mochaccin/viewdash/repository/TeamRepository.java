package com.mochaccin.viewdash.repository;


import com.mochaccin.viewdash.model.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<Team, ObjectId> {
    Optional<Team> findTeamByName(String name);
    boolean existsByName(String name);
}
