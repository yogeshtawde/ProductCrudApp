package in.belenits.service;


import in.belenits.entity.Product;
import in.belenits.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ProductServiceImpl implements IProductService
{


    @Autowired
    private IProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public String addProduct(Product product)
    {
        logger.info("Add Product Request Received");
        logger.debug("Product Details : {}", product);

        Product savedProduct = productRepository.save(product);

        logger.info("Product Saved Successfully with Id : {}", savedProduct.getProductId());
        return  "Product Added successfully! ";
    }


    @Override
    public Product getProduct(Integer productId) {

        logger.info("Get Product Request Received for Product Id : {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.warn("Product Not Found with Id : {}", productId);
                    return new RuntimeException("Product not found");
                });

        logger.info("Product Retrieved Successfully : {}", product.getProductName());

        return product;
    }


    @Override
    public String updateProduct(Integer productId, Product product) {

        logger.info("Update Product Request Received for Product Id : {}", productId);

        Optional<Product> byId = productRepository.findById(productId);

        if (byId.isPresent()) {

            Product existingProduct = byId.get();

            logger.debug("Existing Product Details : {}", existingProduct);

            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setCatagory(product.getCatagory());
            existingProduct.setQuantity(product.getQuantity());

            productRepository.save(existingProduct);

            logger.info("Product Updated Successfully for Product Id : {}", productId);

            return "Product details updated successfully";
        }

        logger.warn("Update Failed. Product Not Found with Id : {}", productId);

        return "Product not found";
    }


    @Override
    public String deleteProduct(Integer productId) {

        logger.info("Delete Product Request Received for Product Id : {}", productId);

        Optional<Product> byId = productRepository.findById(productId);

        if (byId.isPresent()) {

            productRepository.deleteById(productId);

            logger.info("Product Deleted Successfully with Id : {}", productId);

            return "Product deleted";
        }

        logger.warn("Delete Failed. Product Not Found with Id : {}", productId);

        return "Product not found";
    }


}
