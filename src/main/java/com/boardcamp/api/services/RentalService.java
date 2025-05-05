package com.boardcamp.api.services;
import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.GenericNotFoundException;
import com.boardcamp.api.exceptions.GenericUnprocessableEntityException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    final RentalRepository rentalRepository;

    @Autowired
     final CustomerRepository customerRepository;

    @Autowired
    final GameRepository gameRepository;


    RentalService(RentalRepository rentalRepository, CustomerRepository customerRepository, GameRepository gameRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.gameRepository = gameRepository;
    }

    public List<RentalModel> findAll() {
        return rentalRepository.findAll();
    }

    public RentalModel save(RentalDTO dto) {
        CustomerModel customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(
                () -> new GenericNotFoundException("Customer not found")
        );

        GameModel game = gameRepository.findById(dto.getGameId()).orElseThrow(
                () -> new GenericNotFoundException("Game not found")
        );

        List<RentalModel> gameRentals = rentalRepository.findAllByGame(game);

        if (gameRentals.size() >= game.getStockTotal()) {
            throw new GenericUnprocessableEntityException("This game is out of stock");
        }

        LocalDate rentDate = LocalDate.now();
        Double originalPrice = dto.getDaysRented() * game.getPricePerDay();
        Double delayFee = 0.0;

        RentalModel rental = new RentalModel(dto, customer, game);
        rental.setRentDate(rentDate);
        rental.setOriginalPrice(originalPrice);
        rental.setDelayFee(delayFee);

        return rentalRepository.save(rental);

    }

    public RentalModel finishRental(Long id) {
        RentalModel rental = rentalRepository.findById(id).orElseThrow(
                () -> new GenericNotFoundException("Rental not found")
        );

        if (rental.getReturnDate() != null) {
            throw new GenericUnprocessableEntityException("This rental is already finished");
        }
        LocalDate returnDate = LocalDate.now();
        rental.setReturnDate(returnDate);
        Long delayedDays = ChronoUnit.DAYS.between(rental.getRentDate(), returnDate);

        if (delayedDays > rental.getDaysRented()) {
            Double dalayFee = delayedDays * rental.getDelayFee();
            rental.setDelayFee(dalayFee);
        }

        return rentalRepository.save(rental);
    }
}
