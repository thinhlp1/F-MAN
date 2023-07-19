package com.poly.fman.controller.guest;

import com.poly.fman.entity.Brand;
import com.poly.fman.entity.Product;
import com.poly.fman.service.BrandService;
import com.poly.fman.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class HomepageController {

    private final ProductService productService;
    private final BrandService brandService;

    @GetMapping("/home")
    public String homepage() {
        return "user/index";
    }

    @ModelAttribute("items")
    public Page<Product> getListProduct(
            Model model) {
        int pageSize = (int) productService.getCountProduct();

        Pageable pageable = PageRequest.of(0, pageSize, Sort.by("createAt").descending());
        Page<Product> page = (Page<Product>) productService.getAllActive(pageable);
        return page;
    }

    @ModelAttribute("product_selling")
    public Page<Product> getListProductSelling(
            Model model) {
        int pageSize = (int) productService.getCountProduct();

        Pageable pageable = PageRequest.of(0, pageSize, Sort.by("id").descending());
        Page<Product> page1 = (Page<Product>) productService.getTopProductSelling(pageable);
        return page1;
    }

    @ModelAttribute("brands")
    public List<Brand> getListBrand(){
        return brandService.getAllActive();
    }
}
