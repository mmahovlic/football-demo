package com.football.league.mapper

import com.football.league.controller.model.FootballClub
import com.football.league.repository.model.FootballClubEntity

class FootballClubMapper {

    companion object {
        fun mapToEntity(footballClub: FootballClub): FootballClubEntity = FootballClubEntity(footballClub.id, footballClub.name)

        fun mapFromEntity(entity: FootballClubEntity): FootballClub = FootballClub(entity.id, entity.name!!)
    }

}