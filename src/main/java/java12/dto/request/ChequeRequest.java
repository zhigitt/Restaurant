package java12.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChequeRequest
{
    List<Long> menuItemIds;
}
