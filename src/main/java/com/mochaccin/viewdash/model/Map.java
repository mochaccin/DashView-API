package com.mochaccin.viewdash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a map and its associated statistics in the context of a player's performance.
 * This class includes fields like name, win rate, number of times played, rounds won by teams, etc.
 */
@Data
@AllArgsConstructor
public class Map {

    private String name;          // Name of the map

    private String imgURL;        // Image URL for the map

    private double winRate;       // Win rate on this map

    private int timesPlayed;      // Number of times this map has been played

    private int ctRoundsWon;      // Number of rounds won by the Counter-Terrorist side on this map

    private int tRoundsWon;       // Number of rounds won by the Terrorist side on this map

    private double rating;        // Overall rating on this map

    private int clutchesWon;      // Number of clutches won on this map
}
