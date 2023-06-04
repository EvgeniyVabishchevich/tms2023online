package by.tms.eshopspringboot.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchParams {
    private String searchRequest;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
