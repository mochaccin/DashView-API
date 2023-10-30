package com.mochaccin.viewdash.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.HashMap;
import java.util.List;

@Document(collection = "Team")
@Data
@AllArgsConstructor
public class Team {

    @Id
    private ObjectId objectId;
    private String name;
    private String imgUrl;

    @DocumentReference
    private List<Player> players;

    private List<Coach> coaches;
    private HashMap<String, String> teamPlaystyle;
    private HashMap<String, String> teamStats;
}
