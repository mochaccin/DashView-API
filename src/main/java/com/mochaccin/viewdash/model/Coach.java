package com.mochaccin.viewdash.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a coach in the system.
 * This class is annotated as a MongoDB document and includes various fields such as nickname, name, nationality, and others.
 */
@Document(collection = "Coach")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {

    @Id
    ObjectId objectId;  // Unique identifier for the Coach

    @NotNull @NotBlank
    private String nickname;  // Nickname of the Coach

    @NotNull @NotBlank
    private String name;      // Full name of the Coach

    @NotNull @NotBlank
    private String nationality; // Nationality of the Coach

    @NotNull @NotBlank
    private String imgUrl;    // Image URL for the Coach

    @NotNull @NotBlank
    private String role;      // Role or position of the Coach
}