package java12.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GetCheckResponse(
        ChequeResponse chequeResponse,
        List<MenuResponse> menuItems,
        String percent,
        Double grandTotal) {
}
