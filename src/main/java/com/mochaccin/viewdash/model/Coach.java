package com.mochaccin.viewdash.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Coach")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {

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
    private String imgUrl;
    @NotNull
    @NotBlank
    private String role;
}
