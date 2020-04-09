package cn.litrc.order.service.impl;

import cn.litrc.order.entity.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findById() {
        ProductCategory productCategory = categoryService.findById(1).orElse(null);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2));
        Assert.assertNotEquals(1, productCategoryList.size());

    }

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生专享");
        productCategory.setCategoryType(10);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}