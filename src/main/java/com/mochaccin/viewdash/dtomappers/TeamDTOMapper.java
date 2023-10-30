package com.mochaccin.viewdash.dtomappers;

import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TeamDTOMapper implements Function<Team, TeamDTO> {
    @Override
    public TeamDTO apply(Team team) {
        return new TeamDTO(team.getObjectId().toHexString(), team.getName(), team.getImgUrl());
    }
}
