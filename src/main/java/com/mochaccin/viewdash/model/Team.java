package com.mochaccin.viewdash.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Team")
@Data
@AllArgsConstructor
public class Team {

    @Id
    private ObjectId objectId;

    private String name;
    private List<Player> players;
    private List<Coach> coaches;
    private List<String> teamPlaystyle;
    private int leaguePosition;
    private int leaguePoints;
    private int totalEarnings;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int winRate;

}
