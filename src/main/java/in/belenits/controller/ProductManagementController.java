package in.belenits.controller;

import in.belenits.dto.ProductDTO;
import in.belenits.response.ApiResponse;
import in.belenits.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequestMapping("/product")
public class ProductManagementController
{

    @Autowired
    private IProductService service;

    private static final Logger logger = LoggerFactory.getLogger(ProductManagementController.class);

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProductDTO>> addProductDetails(@ModelAttribute ProductDTO productDTO) throws IOException
    {

        logger.info("POST Request Received to Save Product");

        ProductDTO result = service.addProduct(productDTO);

        ApiResponse<ProductDTO> response = new ApiResponse<>();

        response.setStatus(201);
        response.setMessage("Product Saved Successfully");
        response.setData(result);

        logger.info("Product Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductDetails(
            @PathVariable Integer productId)
    {

        logger.info("GET Request Received for Product Id : {}", productId);

        ProductDTO productDTO = service.getProduct(productId);

        ApiResponse<ProductDTO> response = new ApiResponse<>();

        response.setStatus(200);
        response.setMessage(
                "Product Details Fetched Successfully");
        response.setData(productDTO);

        return ResponseEntity.ok(response);
    }


    @PutMapping(value = "/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProductDTO>> updateProductDetails(@PathVariable Integer productId, @ModelAttribute ProductDTO productDTO) throws IOException
    {

        logger.info("UPDATE Request Received for Product Id : {}", productId);

        ProductDTO result = service.updateProduct(productId, productDTO);

        ApiResponse<ProductDTO> response = new ApiResponse<>();

        response.setStatus(200);
        response.setMessage("Product Updated Successfully");
        response.setData(result);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDTO>> deleteProductDetails(@PathVariable Integer productId)
    {

        logger.info("DELETE Request Received for Product Id : {}", productId);
        ProductDTO productDTO = service.deleteProduct(productId);
        ApiResponse<ProductDTO> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage("Product Deleted Successfully");
        response.setData(productDTO);

        return ResponseEntity.ok(response);
    }

}