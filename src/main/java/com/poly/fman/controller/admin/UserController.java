package com.poly.fman.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.poly.fman.dto.model.BrandDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.model.RoleDTO;
import com.poly.fman.dto.model.UserDTO;
import com.poly.fman.dto.model.UserDTO2;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.Brand;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.entity.Role;
import com.poly.fman.entity.User;
import com.poly.fman.repository.RoleRepository;
import com.poly.fman.service.UserService;
import com.poly.fman.service.common.DateUtils;
import com.poly.fman.service.common.ParamService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final ParamService paramService;

   @GetMapping("/")
    public String getUsers(){
        return "admin/layout/User/user_list";
    }

	  @GetMapping("/list")
	  @ResponseBody
    public ResponseEntity<List<User>> getUsersAll(){
		return ResponseEntity.ok(userService.getAllActive());       
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        if (!userService.existUserById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getUserByIdAndActiveTrue(id));
    }

    @GetMapping("/detail-form/{userId}")
		public String detailForm(Model model , @PathVariable ("userId") String userId) {

			return "admin/layout/User/user_details";
		}
	

	@GetMapping("/detail/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserDetail(@PathVariable("id") String id) {
        User user = userService.getUser(id);
        
        if (user == null) {
            System.out.println("vao day roi ne");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "admin/layout/User/user_add";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestParam("photo_file") MultipartFile photoFile,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("password") String password,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("numberPhone") String numberPhone,
                                                   @RequestParam("active") boolean activeUser,
                                                   @RequestParam("roleId") String roleId
                                                   ) {
        User userCheck = userService.getUser(email);
     if (userCheck != null){
				System.out.println("vào day la bi trung");
			   return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "username và mail đã tồn tại"));
		     }
   try {
            //   Save file ảnh vào thư mục images
            paramService.saveSpringBootUpdated(photoFile, "src\\main\\resources\\static\\admin-resouce\\plugins\\images\\users");

            // Thêm phương thức thanh toán vào cơ sở dữ liệu
            UserDTO2 userDTO = new UserDTO2();
            userDTO.setName(name);
            userDTO.setUsername(email);
            userDTO.setEmail(email);
            userDTO.setImage(photoFile.getOriginalFilename());
            userDTO.setActive(activeUser);
            userDTO.setNumberPhone(numberPhone);
            userDTO.setCreateAt(new Date());
            userDTO.setPassword(password);
            userDTO.setRoleId(roleId);
              // Kiểm tra xem usser đã tồn tại hay chưa
            if (!userService.exstUserByEmail(email) || !userService.exstUserByPhoneNumber(numberPhone)) {
                  ResponseEntity.notFound().build();
            }         
            userService.create(userDTO);

            // Trả về đối tượng phương thức thanh toán đã được thêm
            return ResponseEntity.ok(userDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @GetMapping("/update-form")
    public String updateForm() {
        return "admin/layout/User/user_update";
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO2> updateCategory(@PathVariable("id") int id,
                                                   @RequestParam("photo_file") MultipartFile photoFile,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("password") String password,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("numberPhone") String numberPhone,
                                                   @RequestParam("active") boolean activeUser,
                                                   @RequestParam("roleId") String roleId) {
       if (!userService.exstUserByUsername(email)) {
            ResponseEntity.notFound().build();
        }

            //   Save file ảnh vào thư mục images
            paramService.saveSpringBootUpdated(photoFile, "src\\main\\resources\\static\\admin-resouce\\plugins\\images\\users");

            // Thêm phương thức thanh toán vào cơ sở dữ liệu
            UserDTO2 userDTO = new UserDTO2();
            userDTO.setName(name);
            userDTO.setUsername(email);
            userDTO.setEmail(email);
            userDTO.setImage(photoFile.getOriginalFilename());
            userDTO.setActive(activeUser);
            userDTO.setNumberPhone(numberPhone);
            userDTO.setCreateAt(new Date());
            userDTO.setPassword(password);
            userDTO.setRoleId(roleId);
            userDTO.setImage(photoFile.getOriginalFilename());

		    userService.update(userDTO, email);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        if (!userService.existUserById(id)) {
            return ResponseEntity.notFound().build();
        }
        User user = new User();
        user = userService.getUserByIdAndActiveTrue(id);
        userService.delete(user.getUsername());
        return ResponseEntity.ok().build();
    }

  
}