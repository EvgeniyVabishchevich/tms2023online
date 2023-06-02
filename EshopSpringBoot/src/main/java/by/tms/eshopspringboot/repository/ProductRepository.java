package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
