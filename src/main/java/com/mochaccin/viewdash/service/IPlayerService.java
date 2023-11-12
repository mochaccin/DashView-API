package com.mochaccin.viewdash.service;

import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.dto.PlayerDTO;

import java.util.List;

public interface IPlayerService {
    List<PlayerDTO> getAllPlayers();
    Player getPlayerById(String id);
    void addPlayer();

    PlayerDTO getPlayerByNickname(String playerNickname);
}
