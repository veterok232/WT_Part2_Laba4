package by.bsuir.laba4.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Hotel room entity class
 */
@Data
@AllArgsConstructor
public class HotelRoom implements BaseEntity {
    /**
     * Room id
     */
    private Integer id;

    /**
     * Room number
     */
    private String roomNumber;

    /**
     * Is occupied
     */
    private Boolean occupied;
}
