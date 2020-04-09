package cn.litrc.order.service.impl;

import cn.litrc.order.entity.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void findById() {
        ProductInfo productInfo = productService.findById("123456").orElse(null);
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    void findUpAll() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }
}