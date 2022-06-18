package com.example.myshoppingapp.product;

import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepositoryUnderTest;
    private Product product;

    @BeforeEach
    void setUp() {
        product =new Product(
                "Amaretto",
                "Good coffee",
                "image",
                35f,
                "Dolce Gusto"
        );
    }

    @Test
    public void findAllProductsTest() {
        // given
        productRepositoryUnderTest.save(product);

        // when
        Iterable<Product> products = productRepositoryUnderTest.findAll();

        // then
        assertThat(products).isNotNull();
    }

    @Test
    public void canFindProductById() {
        // given
        productRepositoryUnderTest.save(product);

        // when
        Optional<Product> productById = productRepositoryUnderTest.findById(product.getId());

        // then
        assertThat(productById).isPresent();
    }

    @Test
    public void canAddProduct() {
        //when
        Product savedProduct = productRepositoryUnderTest.save(product);

        //then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isPositive();
    }

    @Test
    public void deleteUserByIdTest() {
        // given
        productRepositoryUnderTest.save(product);

        // when
        productRepositoryUnderTest.deleteById(product.getId());

        // then
        assertThat(productRepositoryUnderTest.findById(product.getId())).isEmpty();
    }

}
