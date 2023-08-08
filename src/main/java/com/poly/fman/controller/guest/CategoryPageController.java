package com.poly.fman.controller.guest;

import com.poly.fman.entity.Brand;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;
import com.poly.fman.service.BrandService;
import com.poly.fman.service.ProductService;
import com.poly.fman.service.ProductTypeService;
import com.poly.fman.service.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryPageController {

    private final ProductService productService;
    private final ProductTypeService productTypeService;
    private final BrandService brandService;
    private final SizeService sizeService;

    @GetMapping("/")
    public String category() {
        return "user/view/page/category";
    }

    @GetMapping(value = "brand", params = "brand-id")
    public String category(@RequestParam(name = "brand-id") String brandId, Model model) {
        Pageable pageable = PageRequest.of(
                0,
                10,
                Sort.Direction.DESC,
                "id");
        model.addAttribute("items", productService.getAllProductByBrandId(brandId, pageable));
        return "user/view/page/category";
    }

    @PostMapping("fillter")
    public String fillter(@RequestParam(value = "brandId", defaultValue = "%") String brandId,
                          @RequestParam(value = "min_price", defaultValue = "0") BigInteger min,
                          @RequestParam(value = "max_price", defaultValue = "1000000") BigInteger max,
                          Model model) {
        Page<Product> page;
        Pageable pageable = PageRequest.of(
                0,
                20,
                Sort.by(Sort.Direction.DESC,
                        "id"));
        page = (Page<Product>) productService.getAllProductByBrandIdLikeAndProductTypeIdLikeAndPriceBetween(
                brandId,
                ("%"),
                min,
                max,
                pageable
        );
        model.addAttribute("brandId", brandId);
        model.addAttribute("min_price", min);
        model.addAttribute("max_price", max);
        model.addAttribute("items", page);
        return "user/view/page/category";
    }


    @ModelAttribute("list_productType")
    public List<ProductType> getListProductType() {
        return productTypeService.getAll();
    }

    @ModelAttribute("list_brand")
    public List<Brand> getListBrand() {
        return brandService.getAll();
    }

    @ModelAttribute("list_size")
    public List<Size> getListSize() {
        return sizeService.getAll();
    }

    @ModelAttribute("items")
    public Page<Product> getList(
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            @RequestParam("show") Optional<Integer> show,
            @RequestParam("direction") Optional<String> direction,
            @RequestParam("productTypeId") Optional<String> productTypeId,
            @RequestParam("brandId") Optional<String> brandId,
            @RequestParam("min_price") Optional<BigInteger> min,
            @RequestParam("max_price") Optional<BigInteger> max,
            @RequestParam("keywords") Optional<String> keywords,
            Model model) {

        Page<Product> page;
        Pageable pageable = PageRequest.of(
                p.orElse(0),
                show.orElse(8),
                Sort.by(direction.orElse("desc").equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                        field.orElse("id")));
        if (!(productTypeId.isEmpty()) || !(brandId.isEmpty()) || !(min.isEmpty()) || !(max.isEmpty())) {
            page = (Page<Product>) productService.getAllProductByBrandIdLikeAndProductTypeIdLikeAndPriceBetween(
                    brandId.orElse("%"),
                    productTypeId.orElse("%"),
                    min.orElse(BigInteger.valueOf(0)),
                    max.orElse(BigInteger.valueOf(1000000000)),
                    pageable
            );
        } else if (!keywords.isEmpty()) {
            page = (Page<Product>) productService.getProductByNameContain(keywords.orElse(""), pageable);
        } else {
            page = (Page<Product>) productService.getAllActive(pageable);

        }
        model.addAttribute("p", p.orElse(0));
        model.addAttribute("field", field.orElse("price"));
        model.addAttribute("show", show.orElse(8));
        model.addAttribute("direction", direction.orElse(""));
        model.addAttribute("keywords", keywords.orElse(""));
        model.addAttribute("productTypeId", productTypeId.orElse("%"));
        return page;
    }
}
