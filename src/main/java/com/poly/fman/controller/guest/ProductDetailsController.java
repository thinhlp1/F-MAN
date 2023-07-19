package com.poly.fman.controller.guest;

import com.poly.fman.entity.Product;
import com.poly.fman.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ProductDetailsController {

    private final ProductService productService;
    @GetMapping("/product/{id}")
    public String product(@PathVariable("id") String id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("productDetails", product);
        model.addAttribute("listProducts",productService.getAllProductByBrandId(product.getBrand().getId()));
        return "user/view/page/single-product";
    }

}
