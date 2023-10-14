package com.mochaccin.viewdash.repository;

import com.mochaccin.viewdash.model.Coach;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends MongoRepository<Coach, ObjectId> {
}
