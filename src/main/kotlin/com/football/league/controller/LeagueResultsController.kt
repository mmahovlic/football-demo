package com.football.league.controller

import com.football.league.controller.model.LeagueClubResult
import com.football.league.mapper.FootballClubMapper
import com.football.league.repository.FootballMatchResultRepository
import com.football.league.repository.LeagueClubResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional

@RestController
@RequestMapping("/results")
class LeagueResultsController {

    @Autowired
    private lateinit var leagueClubResultRepository: LeagueClubResultRepository

    @GetMapping("/calculate")
    @Transactional
    fun calculateResults() {
        leagueClubResultRepository.deleteAll()
        leagueClubResultRepository.sumPointsAndRankClubs()
    }

    @GetMapping("/get")
    fun getResults(): List<LeagueClubResult> = leagueClubResultRepository.findAllByOrderByIdAsc()
            .map { LeagueClubResult(
                    FootballClubMapper.mapFromEntity(it.footballClub!!),
                    it.rank!!, it.totalPoints!!, it.totalGoalsScored!!, it.totalGoalsTaken!!) }
}