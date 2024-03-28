package java12.dto.request;

import java.time.LocalDate;

public record StopListRequest(
        String reason,
        LocalDate date
) {
}
