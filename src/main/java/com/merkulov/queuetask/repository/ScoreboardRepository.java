package com.merkulov.queuetask.repository;

import com.merkulov.queuetask.entity.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreboardRepository extends JpaRepository<Scoreboard,Long> {

    Scoreboard findTopByOrderByIdDesc();


}
