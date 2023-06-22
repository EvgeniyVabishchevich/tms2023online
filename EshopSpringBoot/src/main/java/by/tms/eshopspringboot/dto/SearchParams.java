package by.tms.eshopspringboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SearchParams {
    private String searchRequest;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
