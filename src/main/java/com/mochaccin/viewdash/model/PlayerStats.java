package com.mochaccin.viewdash.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

/**
 * Represents the statistics of a player in the system.
 * This class is annotated as a MongoDB document and includes a comprehensive set of fields
 * detailing various aspects of a player's performance in games.
 */
@Document(collection = "PlayerStats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStats {

    @Id
    ObjectId objectId;   // Unique identifier for the PlayerStats

    @NotNull @NotBlank
    ObjectId playerId;   // Unique identifier of the associated Player

    private double rating;           // Overall rating of the player
    private double kast;             // Percentage of rounds in which the player had a kill, assist, survived, or was traded
    private double impact;           // Impact score of the player
    private double dpr;              // Deaths per round
    private double adr;              // Average damage per round
    private double kpr;              // Kills per round
    private int totalKills;          // Total number of kills
    private int totalDeaths;         // Total number of deaths
    private double hsPercentage;     // Headshot percentage
    private double kdRatio;          // Kill/Death ratio
    private int totalMapsPlayed;     // Total number of maps played
    private int totalMapsWon;        // Total number of maps won
    private int totalMapsLost;       // Total number of maps lost

    private HashMap<String, Weapon> playerWeaponStats; // Player's statistics with each weapon
    private HashMap<String, Map> playerMapStats;       // Player's performance on different maps
    private HashMap<String, Integer> playerPlaystyle;  // Statistics representing the player's playstyle
}
