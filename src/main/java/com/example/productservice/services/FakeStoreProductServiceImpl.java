package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplate restTemplate;

    FakeStoreProductServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(Double.valueOf(fakeStoreProductDto.getPrice()));
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImage(fakeStoreProductDto.getImage());

        return product;
    }
    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id , FakeStoreProductDto.class);
        if(fakeStoreProductDto == null){
            throw new InvalidProductIdException( id,"Invalid product id");
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
            FakeStoreProductDto[] fakeStoreProductDtos= restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
            List<Product> products = new ArrayList<>();

            for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
                products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
            }
        return  products;

    }

    @Override
    public Product updateProduct() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/"+id , HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct((fakeStoreProductDto));
    }

    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public void deleteProduct() {

    }
}
