package com.mochaccin.viewdash.dtomappers;

import com.mochaccin.viewdash.model.League;
import com.mochaccin.viewdash.model.dto.LeagueDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LeagueDTOMapper implements Function<League, LeagueDTO> {

    @Override
    public LeagueDTO apply(League league) {
        return new LeagueDTO(
                league.getObjectId().toHexString(),
                league.getName(),
                league.getImgUrl()
        );
    }
}
