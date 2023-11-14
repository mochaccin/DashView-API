package com.mochaccin.viewdash.dtomappers;

import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Service class for mapping Team entities to TeamDTO objects.
 * Implements the Function interface for converting a Team object into a TeamDTO.
 */
@Service
public class TeamDTOMapper implements Function<Team, TeamDTO> {

    /**
     * Converts a Team entity into a TeamDTO object.
     *
     * @param team The Team entity to be converted.
     * @return A TeamDTO object containing the data from the provided Team entity.
     */
    @Override
    public TeamDTO apply(Team team) {
        return new TeamDTO(team.getObjectId().toHexString(), team.getName(), team.getImgUrl());
    }
}
