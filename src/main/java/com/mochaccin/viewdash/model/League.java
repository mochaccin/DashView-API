package com.mochaccin.viewdash.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "League")
@Data
@AllArgsConstructor
public class League {

    @Id
    private ObjectId objectId;

    private String name;
    private List<Team> teams;
    private String imgUrl;
}
