package cn.litrc.order.service;

import cn.litrc.order.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<ProductCategory> findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
