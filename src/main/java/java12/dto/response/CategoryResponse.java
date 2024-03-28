package java12.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record CategoryResponse(Long id, String name ) {
}
