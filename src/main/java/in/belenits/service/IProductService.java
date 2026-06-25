package in.belenits.service;

import in.belenits.dto.ProductDTO;

import java.io.IOException;

public interface IProductService {

    ProductDTO addProduct(ProductDTO productDTO) throws IOException;

    ProductDTO getProduct(Integer productId);

    ProductDTO updateProduct(Integer productId, ProductDTO productDTO) throws IOException;

    ProductDTO deleteProduct(Integer productId);
}