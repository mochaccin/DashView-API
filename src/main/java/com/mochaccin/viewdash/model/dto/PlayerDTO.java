package com.mochaccin.viewdash.model.dto;

/**
 * Data Transfer Object (DTO) for a player.
 * This record encapsulates player data, making it suitable for data transfer operations.
 */
public record PlayerDTO(
        String playerId,    // The unique identifier of the player
        String nickname,    // The player's nickname
        String name,        // The full name of the player
        String team,        // The name of the team the player belongs to
        String nationality, // The nationality of the player
        String birthday,    // The player's date of birth
        String role,        // The role of the player within the team
        String imgUrl       // The image URL for the player
) {
}