package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository  extends MongoRepository<Player, ObjectId> {
    Optional<Player> findPlayerByNickname(String nickname);
    boolean existsByNickname(String nickname);
}
