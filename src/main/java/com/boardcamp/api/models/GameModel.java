
package com.boardcamp.api.models;
import com.boardcamp.api.dtos.GameDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class GameModel {

    public GameModel(GameDTO dto) {
        this.name = dto.getName();
        this.image = dto.getImage();
        this.stockTotal = dto.getStockTotal();
        this.pricePerDay = dto.getPricePerDay();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    private String image;

    @Column(nullable = false)
    private Integer stockTotal;

    @Column(nullable = false)
    private Double pricePerDay;

}
