package cart.repository;

import cart.dao.ProductDao;
import cart.domain.product.Product;
import cart.domain.vo.Quantity;
import cart.dao.entity.ProductEntity;
import cart.exception.ProductException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final ProductDao productDao;

    public ProductRepository(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Long saveProduct(ProductEntity productEntity) {
        return productDao.insertProduct(productEntity);
    }

    public List<Product> findAll() {
        return productDao.getAllProducts()
                .stream()
                .map(ProductEntity::toDomain)
                .collect(Collectors.toList());
    }

    public Product findByProductId(Long productId) {
        return productDao.getByProductId(productId)
                .orElseThrow(ProductException.NotFound::new)
                .toDomain();
    }

    public void updateProduct(Long productId, ProductEntity productEntity) {
        productDao.updateProduct(productId, productEntity);
    }

    public void updateMinusQuantity(Long productId, Quantity quantity) {
        productDao.updateMinusQuantity(productId, quantity);
    }

    public void deleteProduct(Long productId) {
        productDao.deleteByProductId(productId);
    }
}
