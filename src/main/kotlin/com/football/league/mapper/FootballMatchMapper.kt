package com.football.league.mapper

import com.football.league.controller.model.FootballMatchData
import com.football.league.controller.model.FootballMatchResult
import com.football.league.repository.model.FootballMatchResultEntity

class FootballMatchMapper {

    companion object {

        fun mapFromData(footballMatchData: FootballMatchData, points: Pair<Int, Int>): List<FootballMatchResultEntity> {
            return listOf(
                    FootballMatchResultEntity(id = null,
                            footballClub = FootballClubMapper.mapToEntity(footballMatchData.firstClub),
                            goalsScored = footballMatchData.score.first,
                            goalsTaken = footballMatchData.score.second,
                            pointCount = points.first),
                    FootballMatchResultEntity(id = null,
                            footballClub = FootballClubMapper.mapToEntity(footballMatchData.secondClub),
                            goalsScored = footballMatchData.score.second,
                            goalsTaken = footballMatchData.score.first,
                            pointCount = points.second)
            )
        }

        fun mapFromEntity(resultEntity: FootballMatchResultEntity): FootballMatchResult = FootballMatchResult(resultEntity.id!!,
                FootballClubMapper.mapFromEntity(resultEntity.footballClub!!),
                resultEntity.goalsScored!!,
                resultEntity.goalsTaken!!,
                resultEntity.pointCount!!)
    }
}