package java12.dto.request;

import java.math.BigDecimal;

public record MenuRequest(
        String name,
        String image,
        int price,
        String description,
        boolean isVegetarian
) {
}
