package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Override
    List<Product> findAll(Specification<Product> spec);
    List<Product> findByIdIn(List<Integer> ids);
}
