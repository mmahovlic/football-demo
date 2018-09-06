package com.football.league.repository.model

import com.football.league.controller.model.FootballClub
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class FootballMatchResultEntity(

        @Id
        @GeneratedValue
        var id: Long? = null,

        @OneToOne
        @JoinColumn(name = "club_id")
        var footballClub: FootballClubEntity? = null,

        @Column
        @NotNull
        var goalsScored: Int? = null,

        @Column
        @NotNull
        var goalsTaken: Int? = null,

        @Column
        @NotNull
        var pointCount: Int? = null
)