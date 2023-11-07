package com.mochaccin.viewdash.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Coach")
@Data
@AllArgsConstructor
public class Coach {

    @Id
    ObjectId objectId;

    private String nickname;
    private String name;
    private String nationality;
    private String imgUrl;
    private String role;
}
