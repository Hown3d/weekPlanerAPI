package de.ludi.weekPlanerAPI.service;

import de.ludi.weekPlanerAPI.dao.ProductDAO;
import de.ludi.weekPlanerAPI.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private ProductDAO productDAO;

  @Autowired
  public ProductService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  public Optional<Product> getProduct(int productID) {
    return productDAO.findById(productID);
  }

  public List<Product> getAllProducts() {
    return productDAO.findAll();
  }

  public Product updateProduct(int productID, Product updatedProduct) {
    return productDAO.findById(productID).map(product -> {
      product.setName(updatedProduct.getName());
      product.setSection(updatedProduct.getSection());
      return productDAO.save(product);
    }).orElseGet(() -> {
      updatedProduct.setId(productID);
      return productDAO.save(updatedProduct);
    });
  }

  public void deleteProduct(int productID) {
    productDAO.deleteById(productID);
  }

  public Product createProduct(Product product) {
    return productDAO.save(product);
  }
}
