package com.boardcamp.api.controllers;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameModel>> getGames() {
        List<GameModel> games = gameService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @PostMapping
    public ResponseEntity<GameModel> createGame(@RequestBody @Valid GameDTO body) {
        GameModel game = gameService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }
}
