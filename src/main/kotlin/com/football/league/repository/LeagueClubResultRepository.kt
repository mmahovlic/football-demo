package com.football.league.repository

import com.football.league.repository.model.LeagueClubResultEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface LeagueClubResultRepository : JpaRepository<LeagueClubResultEntity, Long> {

    @Query(nativeQuery = true,
            value = """INSERT INTO league_club_result_entity(id,rank,total_goals_scored,total_goals_taken,total_points,club_id)
                WITH total_sums AS(
                select club_id, sum(point_count) as total_points, sum(goals_scored) as total_goals_scored, sum(goals_taken) as total_goals_taken
                from
                football_match_result_entity group by club_id)
                SELECT nextval('hibernate_sequence'),
                rank() over (order by total_points DESC, (total_goals_scored - total_goals_taken) DESC) as rank,
                total_goals_scored,
                total_goals_taken,
                total_points,
                club_id
                from total_sums""")
    @Modifying
    fun sumPointsAndRankClubs()

    @Query
    fun findAllByOrderByIdAsc(): List<LeagueClubResultEntity>

}