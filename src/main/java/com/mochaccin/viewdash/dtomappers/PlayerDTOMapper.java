package com.mochaccin.viewdash.dtomappers;

import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.PlayerDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Service class for mapping Player entities to PlayerDTO objects.
 * Implements the Function interface for converting a Player object into a PlayerDTO.
 */
@Service
public class PlayerDTOMapper implements Function<Player, PlayerDTO> {

    /**
     * Converts a Player entity into a PlayerDTO object.
     *
     * @param player The Player entity to be converted.
     * @return A PlayerDTO object containing the data from the provided Player entity.
     */
    @Override
    public PlayerDTO apply(Player player) {
        return new PlayerDTO(player.getObjectId().toHexString(), player.getNickname(), player.getName(), player.getTeam(), player.getNationality(),
                player.getBirthday(), player.getRole(), player.getImgUrl()
        );
    }
}
