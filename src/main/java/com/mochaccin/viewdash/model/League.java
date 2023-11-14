package com.mochaccin.viewdash.model;

import com.mochaccin.viewdash.model.dto.TeamDTO;
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
 * Represents a league in the system.
 * This class is annotated as a MongoDB document and includes fields such as name, teams, teamsDetails, and imgUrl.
 */
@Document(collection = "League")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {

    @Id
    private ObjectId objectId;  // Unique identifier for the League

    @NotNull @NotBlank
    private String name;        // Name of the League

    @DocumentReference
    private List<Team> teams;   // List of teams participating in the League

    private HashMap<String, TeamLeagueDetails> teamsDetails; // Detailed information of teams in the League

    @NotBlank @NotNull
    private String imgUrl;      // Image URL for the League
}
