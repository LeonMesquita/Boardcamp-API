package com.boardcamp.api.models;

import com.boardcamp.api.dtos.RentalDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class RentalModel {

    public RentalModel(RentalDTO dto, CustomerModel customer, GameModel game) {
        this.customer = customer;
        this.game = game;
        this.daysRented = dto.getDaysRented();
        this.returnDate = dto.getReturnDate();
        this.originalPrice = dto.getOriginalPrice();
        this.delayFee = dto.getDelayFee();
        this.rentDate = dto.getRentDate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private GameModel game;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerModel customer;

    @Column(nullable = false)
    private Integer daysRented;

    @Column
    private LocalDate returnDate;

    @Column(nullable = false)
    private Integer originalPrice;

    @Column
    private Double delayFee = 0.0;

    @Column(nullable = false)
    private LocalDate rentDate;


}