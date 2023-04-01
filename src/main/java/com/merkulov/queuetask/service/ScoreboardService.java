package com.merkulov.queue.service;

import com.merkulov.queue.entity.Scoreboard;
import com.merkulov.queue.repository.ScoreboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScoreboardService {
    private final String SYMBOLS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final String start = "a0a0";
    private final ScoreboardRepository scoreboardRepository;

    public Scoreboard generateNextCode() {

        StringBuilder sb;
        Scoreboard scoreboard = scoreboardRepository.findTopByOrderByIdDesc();
        if (!Objects.isNull(scoreboard)) {
            sb = new StringBuilder(scoreboard.getCode());
            Integer codeLength = sb.length();
            if (sb.toString().equals(maxRank(codeLength).toString())) {  // проверка на макс значение
              
                StringBuilder sbStart= newStartRank(codeLength);
                Scoreboard scoreboardSave = new Scoreboard();
                scoreboardSave.setCode(sbStart.toString());
                scoreboardRepository.save(scoreboardSave);
                return scoreboardSave;
            }
            for (int i = codeLength - 1; i >= 0; i--) {
                char currentChar = sb.charAt(i);

                if (currentChar == '9') {
                    sb.setCharAt(i, '0');

                } else if (currentChar == 'z') {
                    sb.setCharAt(i, 'a');

                } else {
                    int currentCharIndex = SYMBOLS.indexOf(currentChar);
                    char nextChar = SYMBOLS.charAt(currentCharIndex + 1);
                    sb.setCharAt(i, nextChar);
                    break;
                }
            }
            Scoreboard scoreboardSave = new Scoreboard();
            scoreboardSave.setCode(sb.toString());
            scoreboardRepository.save(scoreboardSave);
            return scoreboardSave;

        } else {
            scoreboard = new Scoreboard();
            scoreboard.setCode(start);
            scoreboardRepository.save(scoreboard);
            return scoreboard;
        }
    }

    public StringBuilder newStartRank(Integer codeLength) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < codeLength + 2; i++) {
            if (i % 2 == 0) {
                stringBuilder.append('a');
            } else {
                stringBuilder.append('0');
            }

        }
        return stringBuilder;
    }

    public StringBuilder maxRank(Integer codeLength) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            if (i % 2 == 0) {
                stringBuilder.append( 'z');
            } else {
                stringBuilder.append( '9');
            }
        }
        return stringBuilder;
    }
}
