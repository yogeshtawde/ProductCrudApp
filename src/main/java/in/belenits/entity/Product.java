package in.belenits.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Products")
@Getter
@Setter
public class Product
{

    @Id
    private Integer productId;

    @Column(length = 30)
    private String productName;

    private Double productPrice;

    private Integer quantity;

    @Column(length = 30)
    private String catagory;

    @Column(length = 30)
    private String activeSW;

    private String photoName;

    private String photoPath;

    private String pdfName;

    private String pdfPath;
}