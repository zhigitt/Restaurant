package java12.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_seq")
    @SequenceGenerator(name = "cheque_seq", allocationSize = 1)
    private Long id;
    private BigDecimal priceAvg;
    private ZonedDateTime createdAt;
    private double service;
    private int totalPrice;

    public double procient = 0.3;
    @ManyToOne
    private User user;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private List<MenuItem> menuItems;

    public void addMenuItem(MenuItem menuItem) {
        if (this.menuItems == null) this.menuItems = new ArrayList<>();
        this.menuItems.add(menuItem);

    }

    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.createdAt = ZonedDateTime.now();
    }

}
