package com.football.league.controller.model

import com.fasterxml.jackson.annotation.JsonFormat

data class FootballMatchData(

        val id: Long?,
        val firstClub: FootballClub,
        val secondClub: FootballClub,

        @JsonFormat(shape = JsonFormat.Shape.ARRAY)
        val score: Pair<Int, Int>
)