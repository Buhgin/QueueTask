package com.merkulov.queue.repository;

import com.merkulov.queue.entity.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreboardRepository extends JpaRepository<Scoreboard,Long> {


    Scoreboard findTopByOrderByIdDesc();


}
