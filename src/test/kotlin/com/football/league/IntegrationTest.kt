package com.football.league

import com.football.league.controller.FootballClubController
import com.football.league.controller.FootballMatchController
import com.football.league.controller.LeagueResultsController
import com.football.league.controller.model.FootballClub
import com.football.league.controller.model.FootballMatchData
import com.football.league.repository.FootballClubRepository
import com.football.league.repository.FootballMatchResultRepository
import com.football.league.repository.LeagueClubResultRepository
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class IntegrationTest {

	@Autowired
	private lateinit var footballClubController: FootballClubController

	@Autowired
	private lateinit var footballMatchController: FootballMatchController

	@Autowired
	private lateinit var leagueResultsController: LeagueResultsController

    @Autowired
    private lateinit var footballClubRepository: FootballClubRepository

    @Autowired
    private lateinit var footballMatchResultRepository: FootballMatchResultRepository

    @Autowired
    private lateinit var leagueClubResultRepository: LeagueClubResultRepository

	@Before
	fun setup(){
		leagueClubResultRepository.deleteAll()
		footballMatchResultRepository.deleteAll()
		footballClubRepository.deleteAll()
	}

    //wanted to use h2 in memory database for test but it doesn't support postgres rank function :(
    //postgres needs to be running on localhost:5432 in order to run test, if you have docker installed, use docker-compose up in docker dir to run db
	@Test
	fun shouldInsertClubsAndMatchDataAndCalculateResults() {
		val fc1 = footballClubController.upsert(FootballClub(null, "FK Austria Wien"))
        val fc2 = footballClubController.upsert(FootballClub(null, "First Vienna FC"))
        val fc3 = footballClubController.upsert(FootballClub(null, "FK Austria Wien Amateure"))
        val fc4 = footballClubController.upsert(FootballClub(null, "Austria Graz"))

		footballMatchController.insertMatchData(FootballMatchData(null, fc1, fc2, Pair(2, 3)))
        footballMatchController.insertMatchData(FootballMatchData(null, fc1, fc3, Pair(1, 1)))
        footballMatchController.insertMatchData(FootballMatchData(null, fc3, fc2, Pair(2, 2)))
        footballMatchController.insertMatchData(FootballMatchData(null, fc3, fc2, Pair(3, 1)))
        footballMatchController.insertMatchData(FootballMatchData(null, fc4, fc2, Pair(1, 1)))

		leagueResultsController.calculateResults();
		val results = leagueResultsController.getResults();

		val first = results[0]
		with(first){
			club shouldEqual fc3
			rank shouldEqual 1
			totalPoints shouldEqual 5
			totalGoalsScored shouldEqual 6
			totalGoalsTaken shouldEqual 4

		}
        val second = results[1]
		with(second){
            club shouldEqual fc2
            rank shouldEqual 2
            totalPoints shouldEqual 5
            totalGoalsScored shouldEqual 7
            totalGoalsTaken shouldEqual 8
		}
        val third = results[2]
        with(third){
            club shouldEqual fc4
            rank shouldEqual 3
            totalPoints shouldEqual 1
            totalGoalsScored shouldEqual 1
            totalGoalsTaken shouldEqual 1
        }
        val fourth = results[3]
        with(fourth){
            club shouldEqual fc1
            rank shouldEqual 4
            totalPoints shouldEqual 1
            totalGoalsScored shouldEqual 3
            totalGoalsTaken shouldEqual 4
        }
	}

}
