package com.mochaccin.viewdash.service.impl;

import com.mochaccin.viewdash.model.Map;
import com.mochaccin.viewdash.model.Player;
import com.mochaccin.viewdash.model.PlayerStats;
import com.mochaccin.viewdash.model.Weapon;
import com.mochaccin.viewdash.repository.PlayerRepository;
import com.mochaccin.viewdash.repository.PlayerStatsRepository;
import com.mochaccin.viewdash.service.IPlayerStatsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

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

    @Override
    public boolean addStatsToPlayer(String playerId) {
        try {

            if (playerId.length() != 24) { return false; }

            if (playerStatsRepository.findByPlayerId(new ObjectId(playerId)).isPresent()) {
                return false;
            }

            if (playerRepository.existsById(new ObjectId(playerId))) {
                return randomizePlayerStats(playerId);
            }

            return false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean randomizePlayerStats(String playerId) {
        if (playerId.length() != 24) { return false; }

        try {
            Random rnum = new Random();

            PlayerStats ps = new PlayerStats();

            ps.setPlayerId(new ObjectId(playerId));
            ps.setObjectId(new ObjectId());
            ps.setAdr(Math.floor(rnum.nextDouble(0.4, 1.7) * 100) / 100);
            ps.setDpr(Math.floor(rnum.nextDouble(0.4, 1.7) * 100) / 100);
            ps.setKast(Math.floor(rnum.nextDouble(50.0, 100.0) * 100) / 100);
            ps.setKpr(Math.floor(rnum.nextDouble(0.4, 1.7) * 100) / 100);
            ps.setImpact(Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100);
            ps.setHsPercentage(Math.floor(rnum.nextDouble(40.0, 80.0) * 100) / 100);
            ps.setRating(Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100);
            ps.setTotalMapsWon(rnum.nextInt(400, 800));
            ps.setTotalMapsPlayed(rnum.nextInt(400, 1000));
            ps.setTotalKills(rnum.nextInt(10000, 40000));
            ps.setKdRatio(Math.floor(rnum.nextDouble(0.8, 3.0) * 100) / 100);
            ps.setTotalMapsLost(rnum.nextInt(100, 400));
            ps.setTotalDeaths(rnum.nextInt(5000, 25000));


            Map mirage = new Map("mirage", "https://static.csstats.gg/images/maps/icons/cs2/de_mirage.png", rnum.nextInt(40, 100),1200,rnum.nextInt(800, 1600),rnum.nextInt(800, 1600),Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100,rnum.nextInt(10, 100));
            Map overpass = new Map("overpass", "https://static.csstats.gg/images/maps/icons/cs2/de_overpass.png", rnum.nextInt(40, 100),350,rnum.nextInt(250, 700),rnum.nextInt(250, 700),Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100,rnum.nextInt(10, 100));
            Map inferno = new Map("inferno", "https://static.csstats.gg/images/maps/icons/cs2/de_inferno.png", rnum.nextInt(40, 100),800,rnum.nextInt(600, 1100),rnum.nextInt(600, 1100),Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100,rnum.nextInt(10, 100));
            Map anubis = new Map("anubis", "https://static.csstats.gg/images/maps/icons/cs2/de_anubis.png", rnum.nextInt(40, 100),300,rnum.nextInt(200, 580),rnum.nextInt(200, 580),Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100,rnum.nextInt(10, 100));
            Map ancient = new Map("ancient", "https://static.csstats.gg/images/maps/icons/de_ancient.png", rnum.nextInt(40, 100),132,rnum.nextInt(80, 300),rnum.nextInt(80, 300),Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100,rnum.nextInt(10, 50));
            Map vertigo = new Map("vertigo", "https://static.csstats.gg/images/maps/icons/de_vertigo_1.png", rnum.nextInt(40, 100),450,rnum.nextInt(250, 650),rnum.nextInt(250, 650),Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100,rnum.nextInt(10, 100));
            Map nuke = new Map("nuke", "https://static.csstats.gg/images/maps/icons/cs2/de_nuke.png", rnum.nextInt(40, 100),500,rnum.nextInt(300, 750),rnum.nextInt(300, 750),Math.floor(rnum.nextDouble(0.8, 2.0) * 100) / 100,rnum.nextInt(10, 100));

            HashMap<String, Weapon> playerWeaponStats = new HashMap<>();
            HashMap<String, Map> playerMapStats = new HashMap<>();
            HashMap<String, Integer> playerPlaystyle = new HashMap<>();

            playerPlaystyle.put("aggresive", rnum.nextInt(70, 101));
            playerPlaystyle.put("tactical", rnum.nextInt(70, 101));
            playerPlaystyle.put("support", rnum.nextInt(70, 101));
            playerPlaystyle.put("rusher", rnum.nextInt(70, 101));
            playerPlaystyle.put("patient", rnum.nextInt(70, 101));
            playerPlaystyle.put("saver", rnum.nextInt(70, 101));
            playerPlaystyle.put("secure", rnum.nextInt(70, 101));
            playerPlaystyle.put("effective", rnum.nextInt(70, 101));
            playerPlaystyle.put("teamwork", rnum.nextInt(70, 101));
            playerPlaystyle.put("focus", rnum.nextInt(70, 101));
            playerPlaystyle.put("ambition", rnum.nextInt(70, 101));

            Weapon ak47 = new Weapon("AK47", "https://static.csstats.gg/images/weapons/ak47_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000), rnum.nextInt(15000, 29000), 71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon awp = new Weapon("AWP", "https://static.csstats.gg/images/weapons/awp_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(15000, 29000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon m4a4 = new Weapon("M4A4", "https://static.csstats.gg/images/weapons/m4a1_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(15000, 29000),71.79, rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon m4a1 = new Weapon("M4A1-S", "https://static.csstats.gg/images/weapons/m4a1_silencer_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(15000, 29000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon deagle = new Weapon("DEAGLE", "https://static.csstats.gg/images/weapons/deagle_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(15000, 29000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon usp = new Weapon("USP", "https://static.csstats.gg/images/weapons/usp_silencer_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(15000, 29000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon glock = new Weapon("GLOCK", "https://static.csstats.gg/images/weapons/glock_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(15000, 29000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon tec = new Weapon("TEC-9", "https://static.csstats.gg/images/weapons/tec9_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(15000, 29000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon scout = new Weapon("SCOUT", "https://static.csstats.gg/images/weapons/ssg08_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(6000, 9000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon mp9 = new Weapon("MP9", "https://static.csstats.gg/images/weapons/mp9_light.png",rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(5000, 7000),71.79,rnum.nextInt(15000, 29000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));
            Weapon galil = new Weapon("GALIL", "https://static.csstats.gg/images/weapons/galilar_light.png", rnum.nextInt(1000, 35000),rnum.nextInt(1000, 35000),rnum.nextInt(1000, 29000),71.79,rnum.nextInt(5000, 30000),rnum.nextInt(50000, 300000),rnum.nextInt(10, 100),rnum.nextInt(30, 70),rnum.nextInt(10, 40),rnum.nextInt(10, 40),rnum.nextInt(1, 20),rnum.nextInt(1, 15));


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

            Player player = playerRepository.findById(new ObjectId(playerId)).get();
            player.setPlayerStats(ps);

            playerRepository.save(player);
            playerStatsRepository.insert(ps);
            return true;

        } catch (Exception e) {
           return false;
        }
    }
}
