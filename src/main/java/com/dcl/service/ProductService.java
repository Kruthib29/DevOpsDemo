package com.dcl.service;

import java.util.List;
import com.dcl.request.UpdateProduct;
import com.dcl.dto.ProductDto;
import com.dcl.entity.Product;
import com.dcl.request.AddProduct;

public interface ProductService {
ProductDto addProduct(AddProduct request);
ProductDto getProductById(Integer productId);
List<Product> getAllProduct();
public void deleteProductById(Integer productId);
public ProductDto updateProduct(Integer productId,UpdateProduct request);
}
