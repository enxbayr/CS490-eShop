package edu.miu.eshop.eshopadmin.service;

// HH

import edu.miu.eshop.eshopadmin.domain.Dto.ProductDto;
import edu.miu.eshop.eshopadmin.domain.Product;
import edu.miu.eshop.eshopadmin.domain.ProductStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
	ProductDto getProduct(String productId);
	void save(Product product);
	List<ProductDto> getProductsByCategoryId(String categoryId);

	List<ProductDto> getAll();

	List<ProductDto> getProductsByCategoryName( String categoryid, String categoryName);

	List<ProductDto> getProductsByVendorId(String vendorid);

	List<ProductDto> getProductsOnPromotion();

	ResponseEntity<Product> updateStatus(String id, ProductStatus status);

	ResponseEntity<Product> updateProduct(String productid, ProductDto productDto);
}
