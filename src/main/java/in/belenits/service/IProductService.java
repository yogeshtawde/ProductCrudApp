package in.belenits.service;

import in.belenits.entity.Product;

public interface IProductService
{

    public String addProduct(Product product);
    public Product getProduct(Integer productId);
    public String updateProduct(Integer productId,Product product);
    public String deleteProduct(Integer productId);
}
