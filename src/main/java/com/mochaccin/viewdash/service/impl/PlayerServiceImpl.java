package com.mochaccin.viewdash.service.impl;


import com.mochaccin.viewdash.dtomappers.PlayerDTOMapper;
import com.mochaccin.viewdash.model.*;
import com.mochaccin.viewdash.model.dto.PlayerDTO;
import com.mochaccin.viewdash.repository.*;
import com.mochaccin.viewdash.service.IPlayerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerDTOMapper playerDTOMapper;

    @Override
    public List<PlayerDTO> getAllPlayers() {
        if (!playerRepository.findAll().isEmpty()) {
            return playerRepository.findAll().stream().map(playerDTOMapper).toList();
        }

        return null;
    }

    @Override
    public PlayerDTO getPlayerByNickname(String playerNickname) {
        if (playerNickname.isEmpty()) { return null; }

        if (!playerRepository.existsByNickname(playerNickname)) { return null; }

        return playerDTOMapper.apply(playerRepository.findPlayerByNickname(playerNickname).get());
    }

    @Override
    public boolean createPlayer(Player playerBody) {

        if(!playerRepository.existsByNickname(playerBody.getNickname())) {
            try {
                playerRepository.insert(playerBody);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deletePlayer(String playerId) {
        if (playerId.length() != 24) { return false; }

        ObjectId playerObjectId = new ObjectId(playerId);

        try {
            playerStatsRepository.deleteByPlayerId(playerObjectId);
            for (Team team : teamRepository.findAll()) {
                team.getPlayers().removeIf(player ->
                        player.getObjectId().toHexString().equals(playerId)
                );
                teamRepository.save(team);
            }
            playerRepository.deleteById(playerObjectId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Player getPlayerById(String id) {

        if (id.length() != 24) { return null; }

        ObjectId playerObjectId = new ObjectId(id);

        if (playerRepository.findById(playerObjectId).isEmpty()) { return null; }

        return playerRepository.findById(playerObjectId).get();
    }
}
