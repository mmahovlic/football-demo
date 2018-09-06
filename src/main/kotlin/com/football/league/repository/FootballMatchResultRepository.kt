package com.football.league.repository

import com.football.league.repository.model.FootballMatchResultEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FootballMatchResultRepository : JpaRepository<FootballMatchResultEntity, Long> {

    fun findByFootballClub_Name(name: String) : List<FootballMatchResultEntity>

}