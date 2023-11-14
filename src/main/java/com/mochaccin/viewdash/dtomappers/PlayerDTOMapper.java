package com.mochaccin.viewdash.dtomappers;

import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.Team;
import com.mochaccin.viewdash.model.dto.PlayerDTO;
import com.mochaccin.viewdash.model.dto.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PlayerDTOMapper implements Function<Player, PlayerDTO> {
    @Override
    public PlayerDTO apply(Player player) {
        return new PlayerDTO(player.getObjectId().toHexString(), player.getNickname(), player.getName(), player.getTeam(), player.getNationality(),
                player.getBirthday(), player.getRole(), player.getImgUrl()
        );
    }
}
