package com.football.league.controller.model

data class LeagueClubResult(

        val club: FootballClub,
        val rank: Int,
        val totalPoints: Int,
        var totalGoalsScored: Int,
        var totalGoalsTaken: Int
)