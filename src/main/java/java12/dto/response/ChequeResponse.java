package java12.dto.response;

import java12.entities.MenuItem;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Builder
public record ChequeResponse(
        String fullName,
        List<MenuItem> item,
        BigDecimal avgPrice,
        int servicePercent,
        BigDecimal grandTotal
) {
}
