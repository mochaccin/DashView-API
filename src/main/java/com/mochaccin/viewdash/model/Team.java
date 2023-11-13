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

@Document(collection = "Team")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    private ObjectId objectId;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String imgUrl;

    @DocumentReference
    private List<Player> players;

    private List<Coach> coaches;
    private HashMap<String, String> teamPlaystyle;
    private HashMap<String, String> teamStats;
}
