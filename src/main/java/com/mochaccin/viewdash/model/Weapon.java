package com.mochaccin.viewdash.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
public class Weapon {
    private String name;
    private String imgURL;
    private int timesUsed;
    private int totalKills;
    private int totalHs;
    private double hsPercentage;
    private int totalShots;
    private int totalDamage;
    private double accPercentage;
    private double headHitPercentage;
    private double chestHitPercentage;
    private double torsoHitPercentage;
    private double armsHitPercentage;
    private double legsHitPercentage;
}
