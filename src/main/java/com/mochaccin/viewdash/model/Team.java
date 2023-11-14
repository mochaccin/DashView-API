package com.mochaccin.viewdash.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a team in the system.
 * This class is annotated as a MongoDB document and includes fields such as name, imgUrl, players, and team-related statistics.
 */
@Document(collection = "Team")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    private ObjectId objectId; // Unique identifier for the Team

    @NotNull @NotBlank
    private String name;       // Name of the Team

    @NotNull @NotBlank
    private String imgUrl;     // Image URL for the Team

    @DocumentReference
    private List<Player> players; // List of Players in the Team

    @DocumentReference
    private Coach coach;          // Coach of the Team

    private HashMap<String, String> teamPlaystyle; // Descriptions of the Team's playstyle
    private HashMap<String, String> teamStats;     // Various statistics of the Team
}
