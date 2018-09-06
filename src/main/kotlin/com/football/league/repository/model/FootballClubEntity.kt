package com.football.league.repository.model

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class FootballClubEntity(

        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column(unique = true)
        @NotNull
        var name: String? = null
)

