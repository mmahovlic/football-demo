package com.football.league.repository

import com.football.league.repository.model.FootballClubEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FootballClubRepository : JpaRepository<FootballClubEntity, Long> {

    fun findByName(name: String) : FootballClubEntity

}