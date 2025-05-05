package com.boardcamp.api.services;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.GameNameConflictException;
import com.boardcamp.api.exceptions.GenericBadRequestException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findAll() {
        return gameRepository.findAll();
    }

    public GameModel save(GameDTO dto) {
        Optional<GameModel> existinGame = gameRepository.findByName(dto.getName());
        if (existinGame.isPresent()) {
            throw new GameNameConflictException("This name already exists");
        }

        if (dto.getStockTotal() <= 0) {
            throw new GenericBadRequestException("stockTotal must be higher than 0");
        }

        if (dto.getPricePerDay() <= 0.0) {
            throw new GenericBadRequestException("pricePerDay must be higher than 0");
        }
        GameModel game = new GameModel(dto);

        return gameRepository.save(game);

    }
}
