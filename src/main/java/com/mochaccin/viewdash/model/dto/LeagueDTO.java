package com.mochaccin.viewdash.model.dto;

/**
 * Data Transfer Object (DTO) representing a league.
 * This record is used for transferring league data in a more convenient form.
 */
public record LeagueDTO(
        String leagueId, // The unique identifier of the league
        String name, // The name of the league
        String imgUrl // The image URL associated with the league
) {
}
