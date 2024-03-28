package java12.dto.response;

import java.math.BigDecimal;

public record MenuResponse(Long id,
                           String name,
                           String image,
                           int price,
                           String description,
                           boolean isVegetarian
                           ) {
}
