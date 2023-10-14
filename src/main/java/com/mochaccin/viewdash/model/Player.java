package com.mochaccin.viewdash.model;

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

    private String nickname;
    private String name;
    private String nationality;
    private String birthday;
    private String role;

    @DocumentReference
    private PlayerStats playerStats;
}
