package com.boardcamp.api.repositories;

import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {
    List<RentalModel> findAllByGame(GameModel game);
}
