package BrainERP.Brain.product.repository;

import BrainERP.Brain.product.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel>findByName(String name);
    Optional<ProductModel>findByIdAndActivateTrue(Long id);
    List<ProductModel>findAllByActivateTrue();
}
