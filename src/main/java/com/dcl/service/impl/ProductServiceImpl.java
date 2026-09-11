package com.dcl.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.dto.ProductDto;
import com.dcl.entity.Product;
import com.dcl.exception.AppException;
import com.dcl.repo.ProductRepository;
import com.dcl.request.AddProduct;
import com.dcl.service.ProductService;
import com.dcl.request.UpdateProduct;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prepo;
	
	@Override
	public ProductDto addProduct(AddProduct request) {
		Product p=new Product();
		p.setProductName(request.getProductName());
		p.setPrice(request.getPrice());
		p.setBrand(request.getBrand());
		p=prepo.save(p);
		ProductDto dto=new ProductDto();
		dto.setProductId(p.getProductId());
		dto.setProductName(p.getProductName());
		dto.setPrice(p.getPrice());
		dto.setBrand(p.getBrand());
		return dto;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
	Product p=prepo.findById(productId).orElse(null);
	if(p==null) {
		throw new AppException("Product not found!!", HttpStatus.NOT_FOUND);
	}
	ProductDto dto=new ProductDto();
	dto.setProductId(p.getProductId());
	dto.setProductName(p.getProductName());
	dto.setPrice(p.getPrice());
	dto.setBrand(p.getBrand());
	return dto;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> productList=prepo.findAll();
		Function<Product, ProductDto> function=(p)->{
			ProductDto pdto=new ProductDto();
			pdto.setProductId(p.getProductId());
			pdto.setProductName(p.getProductName());
			pdto.setBrand(p.getBrand());
			pdto.setPrice(p.getPrice());
			return pdto;
		};
		List<ProductDto> dto=productList.stream().map(function).collect(Collectors.toList());
		return productList;
	}

	@Override
	public void deleteProductById(Integer ProductId) {
		Product product=prepo.findById(ProductId).orElseThrow(()->new AppException("Product not found!!", HttpStatus.NOT_FOUND));
		prepo.delete(product);
	}

	@Override
	public ProductDto updateProduct(Integer ProductId, UpdateProduct request) {
		Product existingproduct=prepo.findById(ProductId).orElseThrow(()->new AppException("Product not found!!", HttpStatus.NOT_FOUND));
		   existingproduct.setProductName(request.getProductName());
        existingproduct.setPrice(request.getPrice());
        existingproduct.setBrand(request.getBrand());
        Product afterUpdate=prepo.save(existingproduct);
        ProductDto dto=new ProductDto();
        dto.setProductId(afterUpdate.getProductId());
        dto.setProductName(afterUpdate.getProductName());
        dto.setPrice(afterUpdate.getPrice());
        dto.setBrand(afterUpdate.getBrand());
		return dto;
	}


}
