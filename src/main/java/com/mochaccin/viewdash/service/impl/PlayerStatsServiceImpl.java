package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.model.PlayerStats;
import com.mochaccin.viewdash.repository.PlayerRepository;
import com.mochaccin.viewdash.repository.PlayerStatsRepository;
import com.mochaccin.viewdash.service.IPlayerStatsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatsServiceImpl implements IPlayerStatsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    public PlayerStats getPlayerStatsByPlayerId(String playerId) {

        if (playerId.length() != 24) { return null; }

        ObjectId playerObjectId = new ObjectId(playerId);

        if (playerRepository.findById(playerObjectId).isEmpty()) { return null; }

        if (playerStatsRepository.findByPlayerId(playerObjectId).isEmpty()) { return null; }

        return playerStatsRepository.findByPlayerId(playerObjectId).get();
    }
}
