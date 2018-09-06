package com.football.league.controller

import com.football.league.controller.model.FootballMatchData
import com.football.league.controller.model.FootballMatchResult
import com.football.league.mapper.FootballMatchMapper
import com.football.league.repository.FootballMatchResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/match")
class FootballMatchController {

    private val win_points = 3
    private val draw_points = 1

    @Autowired
    private lateinit var footballMatchResultRepository: FootballMatchResultRepository

    @PostMapping
    fun insertMatchData(@RequestBody footballMatchData: FootballMatchData) : List<FootballMatchResult> {
        val points = resolvePoints(footballMatchData);
        val matchResults = FootballMatchMapper.mapFromData(footballMatchData, points)
        return footballMatchResultRepository.saveAll(matchResults).map { FootballMatchMapper.mapFromEntity(it) }
    }

    @GetMapping
    fun findByClubName(@RequestParam(value = "clubname") name: String) : List<FootballMatchResult> =
         footballMatchResultRepository.findByFootballClub_Name(name).map { FootballMatchMapper.mapFromEntity(it) }

    @DeleteMapping
    fun deleteById(@RequestParam(value = "id")  id: Long) = footballMatchResultRepository.deleteById(id);


    private fun resolvePoints(footballMatchData: FootballMatchData): Pair<Int, Int> = when {
        footballMatchData.score.first > footballMatchData.score.second -> Pair(win_points, 0)
        footballMatchData.score.first < footballMatchData.score.second -> Pair(0, win_points)
        else -> Pair(draw_points, draw_points)
    }

}