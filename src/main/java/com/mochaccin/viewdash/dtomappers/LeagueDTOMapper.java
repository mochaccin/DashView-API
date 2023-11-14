package com.mochaccin.viewdash.dtomappers;

import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Service class for mapping League entities to LeagueDTO objects.
 * This class implements the Function interface, providing a method to convert a League object into a LeagueDTO.
 */
@Service
public class LeagueDTOMapper implements Function<League, LeagueDTO> {

    /**
     * Converts a League entity into a LeagueDTO object.
     *
     * @param league The League entity to be converted.
     * @return A LeagueDTO object containing the data from the provided League entity.
     */
    @Override
    public LeagueDTO apply(League league) {
        return new LeagueDTO(
                league.getObjectId().toHexString(),
                league.getName(),
                league.getImgUrl()
        );
    }
}
