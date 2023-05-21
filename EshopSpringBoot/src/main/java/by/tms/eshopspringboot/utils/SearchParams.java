package by.tms.eshopspringboot.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class SearchParams {
    public static final String ALL_CATEGORIES = "All";
    private String searchRequest = "";
    private String category = ALL_CATEGORIES;
    private BigDecimal minPrice = BigDecimal.ZERO;
    private BigDecimal maxPrice = BigDecimal.ZERO;
}
