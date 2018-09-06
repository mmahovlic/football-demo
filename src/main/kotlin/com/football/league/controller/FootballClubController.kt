package com.football.league.controller

import com.football.league.controller.model.FootballClub
import com.football.league.mapper.FootballClubMapper
import com.football.league.repository.FootballClubRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/club")
class FootballClubController {

    @Autowired
    private lateinit var footballClubRepository: FootballClubRepository

    @PostMapping
    fun upsert(@RequestBody footballClub: FootballClub): FootballClub {
        val entity = FootballClubMapper.mapToEntity(footballClub);
        return FootballClubMapper.mapFromEntity(footballClubRepository.save(entity));
    }

    @GetMapping
    fun findByName(@RequestParam(value = "name") name: String): FootballClub =
            FootballClubMapper.mapFromEntity(footballClubRepository.findByName(name));


    @DeleteMapping
    fun deleteById(@RequestParam(value = "id") id: Long) =
            footballClubRepository.deleteById(id);

}