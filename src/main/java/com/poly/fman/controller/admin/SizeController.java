package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.SizeDTO;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;
import com.poly.fman.service.SizeService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/sizes")
public class SizeController {

    private final SizeService sizeService;
    private final SessionService session;
    private final ParamService paramService;

    @GetMapping("/")
    public String getSizes(Model model) {
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
        return "admin/layout/Size/size_list";
    }

    @ModelAttribute("items")
    public Page<Size> getList(
            @RequestParam("p") Optional<Integer> p,
            @RequestParam("field") Optional<String> field,
            @RequestParam("direction") Optional<String> direction,
            @RequestParam("keywords") Optional<String> keywords,
            Model model) {
        //Khởi tạo một đối tượng Page;
        Page<Size> page;
        //Và biến pageSize để render số lượng element trong 1 page
        int pageSize = 8;
        //Kiểm tra điều kiện nếu người dùng lọc theo những size KHÔNG ACTIVE
        if (field.orElse("").equalsIgnoreCase("inactive")) {
            //Tạo một đối tượng pageable để liệt kê ra một phuongw thức để sort
            Pageable pageable = PageRequest.of(p.orElse(0), pageSize);
            //Lấy biến page khởi tạo ở trên, gán bằng Page vừa tìm kiếm chỉ nhưng size inactive
            page = (Page<Size>) sizeService.getAllActiveIsFalse(pageable);
        } else if (!keywords.isEmpty()) {
            //Tạo một đối tượng pageable để liệt kê ra một phuongw thức để sort
            Pageable pageable = PageRequest.of(
                    p.orElse(0),
                    pageSize,
                    Sort.by(direction.orElse("desc").equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, field.orElse("id")));
            page = sizeService.searchSizeByIdOrSize(keywords.orElse("%"), pageable);
        } else {
            //Ngược lại sẽ tìm theo field mà người dùng chọn
            Pageable pageable = PageRequest.of(
                    p.orElse(0),
                    pageSize,
                    Sort.by(direction.orElse("desc").equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, field.orElse("id")));
            page = (Page<Size>) sizeService.getAllActive(pageable);
        }
        //Gửi biến page kèm theo field để giữ trạng thái lọc
        model.addAttribute("field", field.orElse("id"));
        model.addAttribute("direction", direction.orElse("asc"));
        model.addAttribute("keywords", keywords.orElse(""));

        return page;
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        Size size = new Size();
        model.addAttribute("size", size);
        return "admin/layout/Size/size_add";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable("id") String sizeId) {
        //Khi id được truyền qua lặp tức tìm kiếm theo ID và fill lên form
        Size size = sizeService.getById(sizeId);
        model.addAttribute("size", size);
        //Sau khi tạo mới một size sẽ hiển thị cho người dùng thông báo tạo thành công
        if (session.get("isCreate") != null) {
            model.addAttribute("isCreate", true);
            session.remove("isCreate");
        }
        if (session.get("isUpdate") != null) {
            model.addAttribute("isUpdate", true);
            session.remove("isUpdate");
        }
        return "admin/layout/Size/size_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        //Xóa mềm bằng cách update lại trạng thái cho ACTIVE
        sizeService.delete(id);
        session.set("isDelete", true);
        return "redirect:/admin/sizes/";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable("id") String id) {
        //Restore lại những size đã bị INACTIVE
        sizeService.restore(id);
        session.set("isRestore", true);
        return "redirect:/admin/sizes/";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("size") SizeDTO size, BindingResult result, Model model) {
        boolean isConfirm = paramService.getBoolean("isConfirm", false);
        //Kiểm tra nếu lỗi sẽ quay lại trang tạo size
        if (result.hasErrors()) {
            return "admin/layout/Size/size_add";
        } else {
            if (!sizeService.existId(size.getId()) || isConfirm) {
                //Kiểm tra xem size đã tồn tại hay chưa
                if (!sizeService.existSize(size.getSize())) {
//                Nếu thông tin hợp lệ thì tạo size mới
//                Gửi biến để thông báo cho người dùng
                    sizeService.create(size);
                    if (isConfirm) {
                        session.set("isUpdate", true);
                    } else {
                        session.set("isCreate", true);
                    }
                } else {
                    // Nếu Size đã tồn tại thông báo cho người dùng
                    model.addAttribute("isCreateFasle", true);
                    return "admin/layout/Size/size_add";
                }
            } else {
                // Nếu Size đã tồn tại thông báo cho người dùng
                model.addAttribute("isSizeExsisted", true);
                return "admin/layout/Size/size_add";
            }
        }
        return "redirect:/admin/sizes/update-form/" + size.getId();
    }

    @PostMapping("/update-form/{id}")
    public String update(@Validated @ModelAttribute("size") SizeDTO size, BindingResult result, Model model) {
        //Kiểm tra nếu lỗi sẽ quay lại trang tạo size
        if (result.hasErrors()) {
            return "admin/layout/Size/size_update";
        } else {
            //Kiểm tra xem size đã tồn tại hay chưa
            System.out.println("Size cữ: " + sizeService.getById(size.getId()).getSize());
            System.out.println("Size mới: " + size.getSize());
            if ((size.getSize().floatValue() == sizeService.getById(size.getId()).getSize().floatValue()) || !(sizeService.existSize(size.getSize()))) {
                //Lấy một cục đối tượng bên form và tiến hành setAcvite bằng true
                //Cập nhật lại một cục đối tượng đó
                size.setActive((byte) 1);
                sizeService.update(size);
                session.set("isUpdate", true);
            } else {
                // Nếu Size đã tồn tại thông báo cho người dùng
                model.addAttribute("isUpdateFalse", true);
                return "admin/layout/Size/size_update";
            }
        }
        return "redirect:/admin/sizes/update-form/" + size.getId();
    }


}