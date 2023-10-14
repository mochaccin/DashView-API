package com.mochaccin.viewdash.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
public class Map {
    private String name;
    private String imgURL;
    private double winRate;
    private int timesPlayed;
    private int ctRoundsWon;
    private int tRoundsWon;
    private double rating;
    private int clutchesWon;
}
