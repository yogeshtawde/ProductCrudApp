package in.belenits.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductDTO
{

    private Integer productId;

    private String productName;

    private Double productPrice;

    private Integer quantity;

    private String catagory;

    private String activeSW;

    private MultipartFile photo;

    private MultipartFile productPdf;
}