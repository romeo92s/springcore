package com.sparta.springcore;

import com.sparta.springcore.Product;
import com.sparta.springcore.ProductMypriceRequestDto;
import com.sparta.springcore.ProductRepository;
import com.sparta.springcore.ProductRequestDto;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDto requestDto) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);


        productRepository.save(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();

        return products;
    }
}