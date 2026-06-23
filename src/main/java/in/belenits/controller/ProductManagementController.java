package in.belenits.controller;


import in.belenits.entity.Product;
import in.belenits.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductManagementController
{


    @Autowired
    private IProductService service;

    private static final Logger logger = LoggerFactory.getLogger(ProductManagementController.class);



    @PostMapping("/addProduct")
    public ResponseEntity<String> addProductDetails(@RequestBody Product product)
    {
        logger.info("POST Request Received to Save Product");
        String message = service.addProduct(product);
        logger.info("Product Saved Successfully");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<Product> getProductDetails(@PathVariable Integer productId)
    {

        logger.info("GET Request Received for Product Id : {}", productId);

        Product product = service.getProduct(productId);

        logger.info("Product Details Returned Successfully for Product Id : {}", productId);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<String> updateProductDetails(@PathVariable Integer productId, @RequestBody Product product)
    {

        logger.info("UPDATE Request Received for Product Id : {}", productId);

        String resMsg = service.updateProduct(productId, product);

        logger.info("Update Operation Completed for Product Id : {}", productId);

        return new ResponseEntity<>(resMsg, HttpStatus.OK);
    }


    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProductDetails(@PathVariable Integer productId)
    {

        logger.info("DELETE Request Received for Product Id : {}", productId);

        String resMsg = service.deleteProduct(productId);

        logger.info("Delete Operation Completed for Product Id : {}", productId);

        return new ResponseEntity<>(resMsg, HttpStatus.OK);
    }

    
}
