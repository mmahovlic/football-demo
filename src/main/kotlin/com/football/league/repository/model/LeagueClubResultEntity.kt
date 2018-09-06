package com.football.league.repository.model

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class LeagueClubResultEntity (

        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column
        @NotNull
        var rank: Int? = null,

        @OneToOne
        @JoinColumn(name = "club_id")
        var footballClub: FootballClubEntity? = null,

        @Column
        @NotNull
        var totalPoints: Int? = null,

        @Column
        @NotNull
        var totalGoalsScored: Int? = null,

        @Column
        @NotNull
        var totalGoalsTaken: Int? = null
)