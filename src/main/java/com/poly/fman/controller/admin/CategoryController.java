package com.poly.fman.controller.admin;

import com.poly.fman.dto.ProductTypeDTO;
import com.poly.fman.dto.SizeDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;
import com.poly.fman.service.ProductTypeService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categorys")
@AllArgsConstructor
public class CategoryController {

    private final ProductTypeService productTypeService;
    private final SessionService session;
    private final ParamService paramService;

    @GetMapping("/")
    public String getCategorys(Model model) {
        // Kiểm tra session có tồn tại hay không, đẩy lên jsp và show message
        if (session.get("isDelete") != null) {
            model.addAttribute("isDelete", true);
            //Sau khi hiển thị xong, remove session
            session.remove("isDelete");
        }
        if (session.get("isRestore") != null) {
            model.addAttribute("isRestore", true);
            session.remove("isRestore");
        }
        return "admin/layout/Category/category-list";
    }

    @ModelAttribute("items")
    public Page<ProductType> getList(
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            @RequestParam("direction") Optional<String> direction,
            Model model) {
        //Khởi tạo một đối tượng Page;
        Page<ProductType> page;
        //Và biến pageSize để render số lượng element trong 1 page
        int pageSize = 5;
        //Kiểm tra điều kiện nếu người dùng lọc theo những danh mục KHÔNG ACTIVE
        if (field.orElse("").equalsIgnoreCase("inactive")) {
            //Tạo một đối tượng pageable để liệt kê ra một phuongw thức để sort
            Pageable pageable = PageRequest.of(p.orElse(0), pageSize);
            //Lấy biến page khởi tạo ở trên, gán bằng Page vừa tìm kiếm chỉ nhưng size inactive
            page = (Page<ProductType>) productTypeService.getAllActiveIsFalse(pageable);
        } else {
            //Ngược lại sẽ tìm theo field mà người dùng chọn, mặc định là ID
            Pageable pageable = PageRequest.of(
                    p.orElse(0),
                    pageSize,
                    Sort.by(direction.orElse("desc").equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                    field.orElse("id")));
            page = (Page<ProductType>) productTypeService.getAllActive(pageable);
        }
        //Gửi biến page kèm theo field để giữ trạng thái lọc
        model.addAttribute("field", field.orElse("%"));
        model.addAttribute("direction", direction.orElse("asc"));
        return page;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        ProductType productType = new ProductType();
        model.addAttribute("productType", productType);
        return "admin/layout/Category/category-add";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable("id") String id) {
        ProductType productType = productTypeService.getById(id);
        model.addAttribute("productType", productType);
        //Sau khi tạo mới một size sẽ hiển thị cho người dùng thông báo tạo thành công
        if (session.get("isCreate") != null) {
            model.addAttribute("isCreate", true);
            session.remove("isCreate");
        }
        if (session.get("isUpdate") != null) {
            model.addAttribute("isUpdate", true);
            session.remove("isUpdate");
        }
        return "admin/layout/Category/category-update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        productTypeService.delete(id);
        session.set("isDelete", true);
        return "redirect:/admin/categorys/";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable("id") String id) {
        productTypeService.restore(id);
        session.set("isRestore", true);
        return "redirect:/admin/categorys/";
    }

    @PostMapping("/search")
    public String searchByKeyWords(
            @RequestParam("keywords") String keywords,
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        Page<ProductType> page = productTypeService.searchProductTypeByIdOrName(keywords, pageable);
        model.addAttribute("items", page);
        model.addAttribute("keywords", keywords);
        return "admin/layout/Category/category-list";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("productType") ProductTypeDTO productType, BindingResult result, Model model) {
        boolean isConfirm = paramService.getBoolean("isConfirm", false);
        //Kiểm tra nếu lỗi sẽ quay lại trang tạo danh mục
        if (result.hasErrors()) {
            return "admin/layout/Category/category-add";
        } else {
            //Kiểm tra xem ID danh mục đã tồn tại hay chưa
            if ((!productTypeService.existProductTypeById(productType.getId())) || isConfirm) {
                //Kiểm tra xem ID danh mục đã tồn tại hay chưa
                if (!productTypeService.existProductTypeByName(productType.getName())) {
                    //Nếu thông tin hợp lệ thì tạo danh mục mới
                    //Gửi biến để thông báo cho người dùng
                    productTypeService.create(productType);
                    if (isConfirm) {
                        session.set("isUpdate", true);
                    } else {
                        session.set("isCreate", true);
                    }
                } else {
                    model.addAttribute("isNameExsited", true);
                    return "admin/layout/Category/category-add";
                }
            } else {
                // Nếu Danh mục đã tồn tại thông báo cho người dùng
                model.addAttribute("isCreateFasle", true);
                return "admin/layout/Category/category-add";
            }
        }
        return "redirect:/admin/categorys/update-form/" + productType.getId();
    }

    @PostMapping("/update/{id}")
    public String update(@Validated @ModelAttribute("productType") ProductTypeDTO productType, BindingResult result, @PathVariable("id") String id, Model model) {
        //Kiểm tra nếu lỗi sẽ quay lại trang tạo danh mục
        if (result.hasErrors()) {
            return "admin/layout/Category/category-update";
        } else {
            //Kiểm tra xem tên danh mục đã tồn tại hay chưa
            if (!(productTypeService.existProductTypeByName(productType.getName())) || (productType.getName().equalsIgnoreCase(productTypeService.getById(id).getName()))) {
//                Nếu thông tin hợp lệ thì tạo danh mục mới
//                Gửi biến để thông báo cho người dùng
                productTypeService.update(productType);
                session.set("isUpdate", true);

            } else {
                // Nếu Danh mục đã tồn tại thông báo cho người dùng
                model.addAttribute("isUpdateFalse", true);
                return "admin/layout/Category/category-update";
            }
        }
        return "redirect:/admin/categorys/update-form/" + productType.getId();
    }


}