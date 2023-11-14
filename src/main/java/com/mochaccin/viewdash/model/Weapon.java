package com.mochaccin.viewdash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a weapon and its associated statistics in the context of a player's performance.
 * This class includes fields like name, image URL, usage statistics, kill count, and various accuracy and hit percentage metrics.
 */
@Data
@AllArgsConstructor
public class Weapon {
    private String name;                // Name of the weapon
    private String imgURL;              // Image URL for the weapon
    private int timesUsed;              // Number of times this weapon has been used
    private int totalKills;             // Total number of kills with this weapon
    private int totalHs;                // Total number of headshots with this weapon
    private double hsPercentage;        // Headshot percentage with this weapon
    private int totalShots;             // Total number of shots fired with this weapon
    private int totalDamage;            // Total damage dealt with this weapon
    private double accPercentage;       // Accuracy percentage with this weapon
    private double headHitPercentage;   // Percentage of shots hitting the head
    private double chestHitPercentage;  // Percentage of shots hitting the chest
    private double torsoHitPercentage;  // Percentage of shots hitting the torso
    private double armsHitPercentage;   // Percentage of shots hitting the arms
    private double legsHitPercentage;   // Percentage of shots hitting the legs
}
