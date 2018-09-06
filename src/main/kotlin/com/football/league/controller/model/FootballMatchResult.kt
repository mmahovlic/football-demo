package com.football.league.controller.model

data class FootballMatchResult(

        val id : Long,
        val footballClub: FootballClub,
        val goalsScored: Int,
        val goalsTaken: Int,
        val pointCount: Int
)
