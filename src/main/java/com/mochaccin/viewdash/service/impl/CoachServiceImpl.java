package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.repository.CoachRepository;
import com.mochaccin.viewdash.repository.TeamRepository;
import com.mochaccin.viewdash.service.ICoachService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachServiceImpl implements ICoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public boolean createCoach(Coach coachBody) {

        if (!coachRepository.existsByName(coachBody.getName())) {
            try {
                coachRepository.insert(coachBody);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean deleteCoach(String coachId) {
        if (coachId.length() != 24) { return false; }

        if (coachRepository.existsById(new ObjectId(coachId))) {
            try {
                coachRepository.deleteById(new ObjectId(coachId));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
