package com.merkulov.queuetask.service;

import com.merkulov.queuetask.entity.Scoreboard;
import com.merkulov.queuetask.repository.ScoreboardRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScoreboardServiceTest {

    @Test
    public void testGenerateNextCodeFirstCode() {
        ScoreboardRepository repository = mock(ScoreboardRepository.class);
        ScoreboardService service = new ScoreboardService(repository);

        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setCode("a0a0");
        when(repository.findTopByOrderByIdDesc()).thenReturn(null);
        when(repository.save(any(Scoreboard.class))).thenReturn(new Scoreboard());

        Scoreboard result = service.generateNextCode();

        assertEquals("a0a0", result.getCode());
        verify(repository, times(1)).findTopByOrderByIdDesc();
        verify(repository, times(1)).save(any(Scoreboard.class));
    }

    @Test
    public void testGenerateNextCodeNewCode() {
        ScoreboardRepository repository = mock(ScoreboardRepository.class);
        ScoreboardService service = new ScoreboardService(repository);

        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setCode("a0a0y9z9");
        when(repository.findTopByOrderByIdDesc()).thenReturn(scoreboard);
        when(repository.save(any(Scoreboard.class))).thenReturn(new Scoreboard());

        Scoreboard result = service.generateNextCode();

        assertEquals("a0a0z0a0", result.getCode());
        verify(repository, times(1)).findTopByOrderByIdDesc();
        verify(repository, times(1)).save(any(Scoreboard.class));
    }

    @Test
    public void testGenerateNextCodeMaxCode() {
        ScoreboardRepository repository = mock(ScoreboardRepository.class);
        ScoreboardService service = new ScoreboardService(repository);

        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setCode("z9z9");
        when(repository.findTopByOrderByIdDesc()).thenReturn(scoreboard);
        when(repository.save(any(Scoreboard.class))).thenReturn(new Scoreboard());

        Scoreboard result = service.generateNextCode();

        assertEquals("a0a0a0", result.getCode());
        verify(repository, times(1)).findTopByOrderByIdDesc();
           verify(repository, times(1)).save(any(Scoreboard.class));
    }
}
