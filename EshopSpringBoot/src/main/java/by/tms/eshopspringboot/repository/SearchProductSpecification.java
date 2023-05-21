package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.utils.SearchParams;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static by.tms.eshopspringboot.utils.SearchParams.ALL_CATEGORIES;

public class SearchProductSpecification implements Specification<Product> {
    private final SearchParams searchParams;

    public SearchProductSpecification(SearchParams searchParams) {
        this.searchParams = searchParams;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.or(
                criteriaBuilder.like(root.get("name"), "%" + searchParams.getSearchRequest() + "%"),
                criteriaBuilder.like(root.get("description"), "%" + searchParams.getSearchRequest() + "%")));

        if (searchParams.getMinPrice().compareTo(BigDecimal.ZERO) > 0) {
            predicates.add(criteriaBuilder.gt(root.get("price"), searchParams.getMinPrice()));
        }

        if (searchParams.getMaxPrice().compareTo(BigDecimal.ZERO) > 0) {
            predicates.add(criteriaBuilder.lt(root.get("price"), searchParams.getMaxPrice()));
        }

        if (!searchParams.getCategory().equals(ALL_CATEGORIES)) {
            predicates.add(criteriaBuilder.equal(root.get("category").get("name"), searchParams.getCategory()));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}