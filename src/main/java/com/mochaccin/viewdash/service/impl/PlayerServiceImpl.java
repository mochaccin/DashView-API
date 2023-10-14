package com.mochaccin.viewdash.service.impl;


import com.mochaccin.viewdash.model.*;
import com.mochaccin.viewdash.repository.*;
import com.mochaccin.viewdash.service.IPlayerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(String id) {
        try {
            if (playerRepository.findById(new ObjectId(id)).isPresent()) {
                return playerRepository.findById(new ObjectId(id)).get();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public PlayerStats getPlayerStats(String id) {

        try {
            if (playerStatsRepository.findByPlayerId(new ObjectId(id)).isPresent()) {
                return playerStatsRepository.findByPlayerId(new ObjectId(id)).get();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public void addPlayer() {
        Player player = new Player();
        ObjectId playerId = new ObjectId();
        player.setObjectId(playerId);
        player.setNickname("s1mple");
        player.setName("Oleksandr Kostyliev");
        player.setRole("AWPer");
        player.setBirthday("October 2, 1997");
        player.setNationality("Ukrainian");

        PlayerStats ps = new PlayerStats();
        ps.setPlayerId(playerId);
        ps.setObjectId(new ObjectId());
        ps.setAdr(85.4);
        ps.setDpr(0.63);
        ps.setKast(73.9);
        ps.setKpr(0.85);
        ps.setImpact(1.34);
        ps.setHsPercentage(41.1);
        ps.setRating(1.24);
        ps.setTotalMapsWon(812);
        ps.setTotalMapsPlayed(1200);
        ps.setTotalKills(38676);
        ps.setKdRatio(1.33);
        ps.setTotalMapsLost(388);
        ps.setTotalDeaths(28996);


        Map mirage = new Map("mirage", "https://static.csstats.gg/images/maps/icons/cs2/de_mirage.png", 87,1200,1235,1502,1.92,91);
        Map overpass = new Map("overpass", "https://static.csstats.gg/images/maps/icons/cs2/de_overpass.png", 64,350,432,512,1.68,34);
        Map inferno = new Map("inferno", "https://static.csstats.gg/images/maps/icons/cs2/de_inferno.png", 58,800,1052,911,1.54,25);
        Map anubis = new Map("anubis", "https://static.csstats.gg/images/maps/icons/cs2/de_anubis.png", 69,300,407,542,1.79,17);
        Map ancient = new Map("ancient", "https://static.csstats.gg/images/maps/icons/de_ancient.png", 59,100,156,126,1.55,9);
        Map vertigo = new Map("vertigo", "https://static.csstats.gg/images/maps/icons/de_vertigo_1.png", 71,450,589,602,1.82,13);
        Map nuke = new Map("nuke", "https://static.csstats.gg/images/maps/icons/cs2/de_nuke.png", 56,500,561,492,1.51,19);

        HashMap<String, Weapon> playerWeaponStats = new HashMap<>();
        HashMap<String, Map> playerMapStats = new HashMap<>();
        HashMap<String, String> playerPlaystyle = new HashMap<>();

        playerPlaystyle.put("aggresive", "91");
        playerPlaystyle.put("tactical", "86");
        playerPlaystyle.put("support", "61");
        playerPlaystyle.put("rusher", "76");
        playerPlaystyle.put("patient", "67");
        playerPlaystyle.put("saver", "53");
        playerPlaystyle.put("secure", "87");
        playerPlaystyle.put("effective", "98");
        playerPlaystyle.put("teamwork", "80");
        playerPlaystyle.put("focus", "89");
        playerPlaystyle.put("ambition", "100");

        Weapon ak47 = new Weapon("AK47", "https://static.csstats.gg/images/weapons/ak47_light.png", 6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);
        Weapon awp = new Weapon("AWP", "https://static.csstats.gg/images/weapons/awp_light.png", 6727,29140,20920,71.79,43201,2392000,58,62,15,15,6,2);
        Weapon m4a4 = new Weapon("M4A4", "https://static.csstats.gg/images/weapons/m4a1_light.png", 6727,29140,20920,71.79, 43201,2392000,58,62,15,15,6,2);
        Weapon m4a1 = new Weapon("M4A1-S", "https://static.csstats.gg/images/weapons/m4a1_silencer_light.png", 6727,29140,20920,71.79,43201,2392000,58,62,15,15,6,2);
        Weapon deagle = new Weapon("DEAGLE", "https://static.csstats.gg/images/weapons/deagle_light.png", 6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);
        Weapon usp = new Weapon("USP", "https://static.csstats.gg/images/weapons/usp_silencer_light.png", 6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);
        Weapon glock = new Weapon("GLOCK", "https://static.csstats.gg/images/weapons/glock_light.png", 6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);
        Weapon tec = new Weapon("TEC-9", "https://static.csstats.gg/images/weapons/tec9_light.png", 6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);
        Weapon scout = new Weapon("SCOUT", "https://static.csstats.gg/images/weapons/ssg08_light.png", 6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);
        Weapon mp9 = new Weapon("MP9", "https://static.csstats.gg/images/weapons/mp9_light.png",6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);
        Weapon galil = new Weapon("GALIL", "https://static.csstats.gg/images/weapons/galilar_light.png", 6727,29140,20920,71.79,43201,2392000,8,62,15,15,6,2);


        playerMapStats.put("mirage", mirage);
        playerMapStats.put("overpass", overpass);
        playerMapStats.put("inferno", inferno);
        playerMapStats.put("anubis", anubis);
        playerMapStats.put("ancient", ancient);
        playerMapStats.put("vertigo", vertigo);
        playerMapStats.put("nuke", nuke);

        playerWeaponStats.put("ak47", ak47);
        playerWeaponStats.put("awp", awp);
        playerWeaponStats.put("m4a4", m4a4);
        playerWeaponStats.put("m4a1-s", m4a1);
        playerWeaponStats.put("deagle", deagle);
        playerWeaponStats.put("usp", usp);
        playerWeaponStats.put("gloack", glock);
        playerWeaponStats.put("tec-9", tec);
        playerWeaponStats.put("scout", scout);
        playerWeaponStats.put("mp9", mp9);
        playerWeaponStats.put("galil", galil);


        ps.setPlayerMapStats(playerMapStats);
        ps.setPlayerWeaponStats(playerWeaponStats);

        ps.setPlayerPlaystyle(playerPlaystyle);
        player.setPlayerStats(ps);

        playerStatsRepository.insert(ps);
        playerRepository.insert(player);

    }
}
