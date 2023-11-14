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

/**
 * Represents a player in the system.
 * This class is annotated as a MongoDB document and includes various fields such as nickname, name, nationality, etc.
 * It also references player statistics through a PlayerStats object.
 */
@Document(collection = "Player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    ObjectId objectId;   // Unique identifier for the Player

    @NotNull @NotBlank
    private String nickname;  // Nickname of the Player

    @NotNull @NotBlank
    private String name;      // Full name of the Player

    @NotNull @NotBlank
    private String nationality; // Nationality of the Player

    @NotNull @NotBlank
    private String birthday;  // Birthday of the Player

    @NotNull @NotBlank
    private String role;      // Role or position of the Player

    @NotNull @NotBlank
    private String imgUrl;    // Image URL for the Player

    private String team;      // Name of the team the Player belongs to

    @DocumentReference
    private PlayerStats playerStats; // Reference to the Player's statistics
}
