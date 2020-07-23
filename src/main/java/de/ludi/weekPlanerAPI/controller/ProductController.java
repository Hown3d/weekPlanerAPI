package de.ludi.weekPlanerAPI.controller;

import de.ludi.weekPlanerAPI.model.Product;
import de.ludi.weekPlanerAPI.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/productAPI", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductController {

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * get a certain Product specified by the productID <br>
   * URL: /productAPI/{productID} <br>
   * METHOD: Get
   * @param productID Id of the Product
   * @return ResponseEntity with statuscode 200 if Product exists and Product in body or 404 if Product doesnt exist
   */
  @GetMapping("/{productID}")
  public ResponseEntity<Product> getProduct(@PathVariable int productID) {
    return ResponseEntity.of(productService.getProduct(productID));
  }

  /**
   * Gets all Products <br>
   * URL: /productAPI/getAllProducts <br>
   * METHOD: Get
   * @return ResponseEntity with statuscode 200 and All Products as a List in body
   */
  @GetMapping("/getAllProducts")
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  /**
   * Updates a Product <br>
   * URL: /productAPI/{productID} <br>
   * METHOD: Put
   * @param productID Id of the Product
   * @param updatedProduct Product which has been updated in client
   * @return ResponseEntity with statuscode 200 and updated Product in body
   */
  @PutMapping("/{productID}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable int productID, @RequestBody Product updatedProduct) {
    return ResponseEntity.ok(productService.updateProduct(productID, updatedProduct));
  }

  /**
   * deletes a Product <br>
   * URL: /productAPI/{productID} <br>
   * METHOD: Delete
   * @param productID ID of the product which shall be deleted
   */
  @DeleteMapping("/{productID}")
  public void deleteProduct(@PathVariable int productID) {
    productService.deleteProduct(productID);
  }

  /**
   * creates a new Product <br>
   * URL: /productAPI/createProduct<br>
   * METHOD: Post
   * @param product Product which shall be created
   * @return ResponseEntity with statuscode 200 and newly created Product in body
   */
  @PostMapping("/createProduct")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.createProduct(product));
  }

}
