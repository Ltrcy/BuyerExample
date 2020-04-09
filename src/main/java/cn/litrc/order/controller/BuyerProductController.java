package cn.litrc.order.controller;

import cn.litrc.order.VO.ProductInfoVO;
import cn.litrc.order.VO.ProductVO;
import cn.litrc.order.VO.ResultVO;
import cn.litrc.order.entity.ProductCategory;
import cn.litrc.order.entity.ProductInfo;
import cn.litrc.order.service.CategoryService;
import cn.litrc.order.service.ProductService;
import cn.litrc.order.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        List<ProductInfo> productInfoList= productService.findUpAll();
        /* 传统写法
        for (ProductInfo productInfo : productServiceList) {
            categoryTypeList.add(productInfo.getCategoryType());
        } */

        //精简写法(java8 lambda)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            // 建议：不要把数据库的查询（findUpAll）放到for循环中，事件开销大
            // 应该先查出来，再用下列代码进行循环
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
         }

        return ResultVOUtil.success(productVOList);
    }
}
