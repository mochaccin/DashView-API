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

@Document(collection = "League")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {

    @Id
    private ObjectId objectId;

    @NotNull
    @NotBlank
    private String name;
    @DocumentReference
    private List<Team> teams;
    private HashMap<String, TeamLeagueDetails> teamsDetails;
    @NotBlank
    @NotNull
    private String imgUrl;
}
