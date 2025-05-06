package com.boardcamp.api.controllers;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalModel>> getAllRentals() {
        List<RentalModel> rentals = rentalService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @PostMapping
    public ResponseEntity<RentalModel> createRental(@RequestBody @Valid RentalDTO body) {
        RentalModel rental = rentalService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<RentalModel> finishRental(@PathVariable Long id) {
        RentalModel rental = rentalService.finishRental(id);
        return ResponseEntity.status(HttpStatus.OK).body(rental);
    }
}
