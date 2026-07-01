package in.belenits.service;

import in.belenits.configuration.FileStorageUtil;
import in.belenits.dto.ProductDTO;
import in.belenits.entity.Product;
import in.belenits.exception.ProductNotFoundException;
import in.belenits.repository.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    private static final Logger logger =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductDTO addProduct(ProductDTO productDTO)
            throws IOException {

        logger.info("Add Product Request Received");

        Product product = new Product();

        BeanUtils.copyProperties(
                productDTO,
                product,
                "photo",
                "productPdf");

        // Save Photo
        if (productDTO.getPhoto() != null
                && !productDTO.getPhoto().isEmpty()) {

            String photoPath =
                    fileStorageUtil.saveFile(
                            productDTO.getPhoto(),
                            "photos");

            product.setPhotoName(
                    productDTO.getPhoto()
                            .getOriginalFilename());

            product.setPhotoPath(photoPath);
        }

        // Save PDF
        if (productDTO.getProductPdf() != null
                && !productDTO.getProductPdf().isEmpty()) {

            String pdfPath =
                    fileStorageUtil.saveFile(
                            productDTO.getProductPdf(),
                            "pdfs");

            product.setPdfName(
                    productDTO.getProductPdf()
                            .getOriginalFilename());

            product.setPdfPath(pdfPath);
        }

        Product savedProduct =
                productRepository.save(product);

        ProductDTO responseDTO =
                new ProductDTO();

        BeanUtils.copyProperties(
                savedProduct,
                responseDTO);

        logger.info(
                "Product Saved Successfully with Id : {}",
                savedProduct.getProductId());

        return responseDTO;
    }

    @Override
    public ProductDTO getProduct(Integer productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found with Id : "
                                                + productId));

        ProductDTO dto =
                new ProductDTO();

        BeanUtils.copyProperties(
                product,
                dto);

        return dto;
    }

    @Override
    public ProductDTO updateProduct(
            Integer productId,
            ProductDTO productDTO)
            throws IOException {

        Product existingProduct =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found with Id : "
                                                + productId));

        BeanUtils.copyProperties(
                productDTO,
                existingProduct,
                "productId",
                "photo",
                "productPdf");

        // Update Photo
        if (productDTO.getPhoto() != null
                && !productDTO.getPhoto().isEmpty()) {

            String photoPath =
                    fileStorageUtil.saveFile(
                            productDTO.getPhoto(),
                            "photos");

            existingProduct.setPhotoName(
                    productDTO.getPhoto()
                            .getOriginalFilename());

            existingProduct.setPhotoPath(
                    photoPath);
        }

        // Update PDF
        if (productDTO.getProductPdf() != null
                && !productDTO.getProductPdf().isEmpty()) {

            String pdfPath =
                    fileStorageUtil.saveFile(
                            productDTO.getProductPdf(),
                            "pdfs");

            existingProduct.setPdfName(
                    productDTO.getProductPdf()
                            .getOriginalFilename());

            existingProduct.setPdfPath(
                    pdfPath);
        }

        Product updatedProduct =
                productRepository.save(
                        existingProduct);

        ProductDTO responseDTO =
                new ProductDTO();

        BeanUtils.copyProperties(
                updatedProduct,
                responseDTO);

        return responseDTO;
    }

    @Override
    public ProductDTO deleteProduct(
            Integer productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found with Id : "
                                                + productId));
        
        product.setActiveSW("NO");

        Product deletedProduct =
                productRepository.save(product);

        ProductDTO dto =
                new ProductDTO();

        BeanUtils.copyProperties(
                deletedProduct,
                dto);

        return dto;
    }
}