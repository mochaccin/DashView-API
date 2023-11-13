package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.Coach;

public interface ICoachService {
    boolean createCoach(Coach coachBody);

    boolean deleteCoach(String coachId);

}
