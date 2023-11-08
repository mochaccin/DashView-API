package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.dto.TeamDTO;

import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends MongoRepository<League, ObjectId> {



}


