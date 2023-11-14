package com.mochaccin.viewdash.model;

/**
 * Record representing the details of a team's performance in a league.
 * It includes information such as league points and the team's position in the league standings.
 */
public record TeamLeagueDetails(
        int leaguePoints,    // Points accumulated by the team in the league
        int leaguePosition   // Position of the team in the league standings
) { }
