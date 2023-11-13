package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.model.Coach;
import com.mochaccin.viewdash.repository.CoachRepository;
import com.mochaccin.viewdash.service.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachServiceImpl implements ICoachService {

    @Autowired
    CoachRepository coachRepository;

    @Override
    public boolean createCoach(Coach coachBody) {
        try {
            coachRepository.insert(coachBody);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
