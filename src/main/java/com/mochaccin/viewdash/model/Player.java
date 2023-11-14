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

@Document(collection = "Player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    ObjectId objectId;

    @NotNull
    @NotBlank
    private String nickname;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nationality;
    @NotNull
    @NotBlank
    private String birthday;
    @NotNull
    @NotBlank
    private String role;
    @NotNull
    @NotBlank
    private String imgUrl;
    private String team;

    @DocumentReference
    private PlayerStats playerStats;
}
