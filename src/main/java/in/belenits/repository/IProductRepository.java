package in.belenits.repository;

import in.belenits.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<Product,Integer>
{

}
