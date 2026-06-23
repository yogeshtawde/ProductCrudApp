package in.belenits.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="Products")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Product
{

    @Id
    private Integer productId;

    @Column(length = 30)
    private String productName;

    @Column(length = 30)
    private Double productPrice;

    private Integer quantity;

    @Column(length = 30)
    private String catagory;


}
