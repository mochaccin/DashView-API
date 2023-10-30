package com.mochaccin.viewdash.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.HashMap;
import java.util.List;

@Document(collection = "PlayerStats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStats {

    @Id
    ObjectId objectId;

    ObjectId playerId;
    private double rating;
    private double kast;
    private double impact;
    private double dpr;
    private double adr;
    private double kpr;
    private int totalKills;
    private int totalDeaths;
    private double hsPercentage;
    private double kdRatio;
    private int totalMapsPlayed;
    private int totalMapsWon;
    private int totalMapsLost;

    private HashMap<String, Weapon> playerWeaponStats;
    private HashMap<String, Map> playerMapStats;
    private HashMap<String, Integer> playerPlaystyle;
}
