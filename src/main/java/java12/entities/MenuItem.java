package java12.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menuItems")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_seq")
    @SequenceGenerator(name = "items_seq", allocationSize = 1)


    private Long id;

    private String name;
    private String image;
    private int price;
    private String description;
    private boolean isVegetarian;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private SubCategory subCategories ;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private Restaurant restaurant;


    public MenuItem(String name, String image, int price, String description, boolean isVegetarian) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }

}
