package com.mochaccin.viewdash.model.dto;

/**
 * Data Transfer Object (DTO) representing a team.
 * This record is utilized for encapsulating team data in a format convenient for data transfer.
 */
public record TeamDTO(
        String teamId,  // The unique identifier of the team
        String name,    // The name of the team
        String imgUrl   // The image URL associated with the team
) {
}