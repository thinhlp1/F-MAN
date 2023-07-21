package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.ProductDTO2;
import com.poly.fman.dto.model.ProductSizeCreateDTO;
import com.poly.fman.dto.model.ProductSizeDTO2;
import com.poly.fman.entity.Brand;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductHistory;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.entity.ProductType;
import com.poly.fman.entity.Size;

import com.poly.fman.service.BrandService;
import com.poly.fman.service.ProductHistoryService;
import com.poly.fman.service.ProductService;
import com.poly.fman.service.ProductSizeService;
import com.poly.fman.service.ProductTypeService;
import com.poly.fman.service.SizeService;
import com.poly.fman.service.common.ParamService;
import com.poly.fman.service.common.SessionService;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {
	private ModelMapper modelMapper = new ModelMapper();
	private final ProductService productService;
	private final ProductSizeService productSizeService;
	private final SizeService sizeService;
	private final ParamService paramService;
	private final BrandService brandService;
	private final ProductTypeService productTypeService;
	private final SessionService sessionService;
	private final ProductHistoryService productHistoryService;

	@GetMapping("/")
	public String getProducts() {

		return "admin/layout/Product/product-list";
	}


    

	@ModelAttribute("items")
	public Page<Product> getList(
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field,
			Model model) {
		
		model.addAttribute("checkItems", true);
		// Khởi tạo một đối tượng Page;
		Page<Product> page;
		// Và biến pageProduct để render số lượng element trong 1 page
		int pageSize = 8;
		// Kiểm tra điều kiện nếu người dùng lọc theo những sản phẩm KHÔNG ACTIVE
		if (field.orElse("").equalsIgnoreCase("inactive")) {
			// Tạo một đối tượng pageable để liệt kê ra một phuongw thức để sort
			Pageable pageable = PageRequest.of(p.orElse(0), pageSize);
			// Lấy biến page khởi tạo ở trên, gán bằng Page vừa tìm kiếm chỉ nhưng sản phẩm
			// inactive
			page = (Page<Product>) productService.getAllActiveIsFalse(pageable);
		} else {
			// Ngược lại sẽ tìm theo field mà người dùng chọn
			Pageable pageable = PageRequest.of(p.orElse(0), pageSize, Sort.by(field.orElse("id")).descending());
			page = (Page<Product>) productService.getAllActive(pageable);
		}
		// Gửi biến page kèm theo field để giữ trạng thái lọc
		model.addAttribute("field", field.orElse("id"));
		return page;
	}
	
	@ResponseBody
	@PostMapping("listfilter")
	public void filterProductSize (@RequestBody List<String> listproductId) {
		sessionService.set("listSizeFilter", listproductId);
		
	}
	@PostMapping("/")
	public String filterProduct (@RequestParam("brand") String brandId , @RequestParam ("price") String price , Model model ,@RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field) {

		// Khởi tạo một đối tượng Page;
				Page<Product> page = null;
				model.addAttribute("checkItems", true);
				// Và biến pageProduct để render số lượng element trong 1 page
				int pageSize = 8;
				Pageable pageable = PageRequest.of(p.orElse(0), pageSize);
				List<String> listSizeFilter = sessionService.get("listSizeFilter");
		if(brandId.equals("all") && price.equals("all")) {
			page = (Page<Product>) productService.getAllActive(pageable);	
			model.addAttribute("items", page);
//			if (listSizeFilter.get(0).equals("notsize")) {
//				model.addAttribute("items", page);
//			}
			
		}else if (brandId.equals("all") && !price.equals("all")) {
				if (price.equals("0")) {
					BigInteger min = BigInteger.valueOf(0);
					BigInteger max = BigInteger.valueOf(500000);
					page = productService.getAllProductByBrandIdLikeAndPriceBetween("%", min, max, pageable);
					model.addAttribute("items", page);
				}
				if (price.equals("500")) {
					BigInteger min = BigInteger.valueOf(500000);
					BigInteger max = BigInteger.valueOf(1000000);
					page = productService.getAllProductByBrandIdLikeAndPriceBetween("%", min, max, pageable);
					model.addAttribute("items", page);
				}
				if (price.equals("1000")) {
					BigInteger min = BigInteger.valueOf(1000000);
					BigInteger max = BigInteger.valueOf(2000000);
					page = productService.getAllProductByBrandIdLikeAndPriceBetween("%", min, max, pageable);
					model.addAttribute("items", page);
				}
				if (price.equals("2000")) {
					BigInteger min = BigInteger.valueOf(2000000);
					BigInteger max = BigInteger.valueOf(100000000);
					page = productService.getAllProductByBrandIdLikeAndPriceBetween("%", min, max, pageable);
					model.addAttribute("items", page);
				}
				
		}else if (!brandId.equals("all") && !price.equals("all")) {
			if (price.equals("0")) {
				BigInteger min = BigInteger.valueOf(0);
				BigInteger max = BigInteger.valueOf(500000);
				page = productService.getAllProductByBrandIdLikeAndPriceBetween(brandId, min, max, pageable);
				model.addAttribute("items", page);
			}
			if (price.equals("500")) {
				BigInteger min = BigInteger.valueOf(500000);
				BigInteger max = BigInteger.valueOf(1000000);
				page = productService.getAllProductByBrandIdLikeAndPriceBetween(brandId, min, max, pageable);
				model.addAttribute("items", page);
			}
			if (price.equals("1000")) {
				BigInteger min = BigInteger.valueOf(1000000);
				BigInteger max = BigInteger.valueOf(2000000);
				page = productService.getAllProductByBrandIdLikeAndPriceBetween(brandId, min, max, pageable);
				model.addAttribute("items", page);
			}
			if (price.equals("2000")) {
				BigInteger min = BigInteger.valueOf(2000000);
				BigInteger max = BigInteger.valueOf(100000000);
				page = productService.getAllProductByBrandIdLikeAndPriceBetween(brandId, min, max, pageable);
				model.addAttribute("items", page);
			}
		}else if (!brandId.equals("all") && price.equals("all")) {
				BigInteger min = BigInteger.valueOf(0);
				BigInteger max = BigInteger.valueOf(50000000);
				page = productService.getAllProductByBrandIdLikeAndPriceBetween(brandId, min, max, pageable);
				model.addAttribute("items", page);
			}
		
		if (page.hasContent() == false) {
			model.addAttribute("checkItems", false);
		}
		

		
		
	
		return "admin/layout/Product/product-list";
	}
	
	

	@GetMapping("/create")
	public String createForm(Model model) {
		ProductDTO2 product = new ProductDTO2();
		model.addAttribute("product", product);
		return "admin/layout/Product/product-add";
	}

	@PostMapping("/create")
	public String createProductForm(@Validated @ModelAttribute("product") ProductDTO2 product, BindingResult result,
			@RequestParam("photo_file") MultipartFile img, Model model,
			@RequestParam("id_product_type") String IdProductType,
			@RequestParam("id_brand") String id_brand) {
		
		
	 		 	
	 	
	 	
	 	
		if (result.hasErrors()) {
			if (checkFrom(model, img, IdProductType, id_brand) == false) {		
				return "admin/layout/Product/product-add";
			}
				return "admin/layout/Product/product-add";
		}
		
		

		if (checkFrom(model, img, IdProductType, id_brand) == false) {
			return "admin/layout/Product/product-add";
		}

		if (productService.getById(product.getId()) != null) {
			model.addAttribute("message_id", "Id đã tồn tại nhập");
			return "admin/layout/Product/product-add";
		}

		ProductType prdtype = new ProductType();
		prdtype = productTypeService.getById(IdProductType);
		product.setProductType(prdtype);

		Brand brand = new Brand();
		brand = brandService.getById(id_brand);
		product.setBrand(brand);

		if (!img.isEmpty()) {
			String image = paramService.save(img, "/views/admin/plugins/images/");
			product.setImage(image);
		}
		
	  	if(productService.getById(product.getId()) != null) {
    		model.addAttribute("message_id", "Id đã tồn tại nhập");
    		return "admin/layout/Product/product-add";
    	}
    	
    	List<Product> ListproductCheckName = new ArrayList<Product>();	
    	ListproductCheckName = productService.getAll();
    	int checkName = -1;
    	for (int i = 0; i < ListproductCheckName.size() ; i++) {
			if (ListproductCheckName.get(i).getName().equalsIgnoreCase(product.getName())) {
				checkName = i;
				break;
			}
			
		}
    	if (checkName >= 0) {
    		model.addAttribute("message_name", "Tên sản phẩm đã tồn tại");
    		return "admin/layout/Product/product-add";
    	}
    	
    	List<ProductSizeCreateDTO> listSizeProduct = new ArrayList<ProductSizeCreateDTO>();
		listSizeProduct = sessionService.get("listSizeProductNew");
    	int sizeList = listSizeProduct.size();
		if (sizeList == 0) {
			model.addAttribute("message_size_quantity", "Vui lòng chọn size và số lượng cho sản phẩm");
			return "admin/layout/Product/product-add";
		}
		
		for (int i = 0; i < listSizeProduct.size(); i++) {
			if (listSizeProduct.get(i).getQuatity().equals("")) {
				listSizeProduct.get(i).setQuatity("1");
			}
		}
   
    	
		product.setActive((byte) 1);
		product.setCreateAt(new Date());
		Product productthuong = modelMapper.map(product, Product.class);
		productService.addProduct(productthuong);
		
		
		
		for (int i = 0; i < listSizeProduct.size(); i++) {
			for (int j = i+1; j < listSizeProduct.size(); j++) {
				if (listSizeProduct.get(i).getId().equals(listSizeProduct.get(j).getId())) {				
					int quantity = Integer.parseInt(listSizeProduct.get(i).getQuatity());
					quantity += Integer.parseInt(listSizeProduct.get(j).getQuatity());
					listSizeProduct.get(i).setQuatity(quantity+"");
					listSizeProduct.remove(j);
				}
			}
		}

		
		for (int i = 0; i < listSizeProduct.size(); i++) {
			ProductSizeDTO2 productSize = new ProductSizeDTO2();
			productSize.setId(i);
			productSize.setActive((byte) 1);
			productSize.setProduct(productthuong);
			productSize.setQuantity(Integer.parseInt(listSizeProduct.get(i).getQuatity()));
			productSize.setCreateAt(new Date());
			productSize.setSize(sizeService.getById(listSizeProduct.get(i).getId()));
			productSizeService.create(productSize);

			sessionService.remove("listSizeProductNew");

		}
		
		return "admin/layout/Product/product-add";

	}

	@ResponseBody
	@PostMapping("/create/productsize")
	public void createProductSize(@RequestBody List<ProductSizeCreateDTO> productSize) {
		sessionService.set("listSizeProductNew", productSize);

	}

	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") String id, Model model) {
		Product product = productService.getById(id);
		// List<ProductSize> size =
		// productSizeService.getAllByProductID(product.getId());
		model.addAttribute("product", product);
		// model.addAttribute("size",product.getProductSizes());
		// System.out.println(size + "HELLO");
		return "admin/layout/Product/product-details";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") String idProduct) {
		Product product = productService.getById(idProduct);

		// tạo product history
		ProductHistory productHistory = new ProductHistory();
		productHistory.setPrice(product.getPrice());
		productHistory.setProduct(product);
		productHistory.setCreateAt(new Date());
		// thêm product history vào bảng
		productHistoryService.create(productHistory);
		//
		// product.setActive((byte)0);
		// product.setDeleteAt(new Date());
		productService.deleteProductActive(product);
		return "redirect:/admin/products/";
	}

	@GetMapping("/update-form/{id}")
	public String updateForm(Model model, @PathVariable("id") String id) {
		model.addAttribute("product", productService.getById(id));

		// lấy danh sách size của product size hiện lên modal
		List<ProductSize> productSizes = new ArrayList<ProductSize>();
		productSizes = productSizeService.getAllByProductID(id);
		sessionService.set("productSS", productService.getById(id));
		model.addAttribute("listProductSize", productSizes);
		return "admin/layout/Product/product-update";
	}

	@PostMapping("/update-form/{id}")
	public String update(@Validated @ModelAttribute("product") ProductDTO2 product, BindingResult result, Model model,
			@RequestParam("photo_file") MultipartFile img,
			@RequestParam("id_product_type") String IdProductType,
			@RequestParam("id_brand") String id_brand) {
		List<ProductSizeCreateDTO> listSizeProductUpdateQuatity = new ArrayList<ProductSizeCreateDTO>();
		listSizeProductUpdateQuatity = sessionService.get("listSizeProductUpdate");
		if (!img.isEmpty()) {
			System.out.println("loi");
			String image = paramService.save(img, "/views/admin/plugins/images/");
			product.setImage(image);
		} else {
			Product productSs = new Product();
			productSs = sessionService.get("productSS");
			product.setImage(productSs.getImage());
		}

		// lấy product trước khi update
		Product productHtr = productService.getById(product.getId());
		// tạo product history
		ProductHistory productHistory = new ProductHistory();
		productHistory.setPrice(productHtr.getPrice());
		productHistory.setProduct(productHtr);
		productHistory.setCreateAt(new Date());
		// thêm product history vào bảng
		productHistoryService.create(productHistory);
		//

		ProductType prdtype = new ProductType();
		prdtype = productTypeService.getById(IdProductType);
		product.setProductType(prdtype);

		Brand brand = new Brand();
		brand = brandService.getById(id_brand);
		product.setBrand(brand);

		if (!img.isEmpty()) {
			String image = paramService.save(img, "/views/admin/plugins/images/");
			product.setImage(image);
		}
		product.setUpdateAt(new Date());

		Product productthuong = modelMapper.map(product, Product.class);
		// update producte thành công
		productService.updateProduct(productthuong);

		// update product size
		List<ProductSize> listSize = new ArrayList<ProductSize>();
		listSize = productSizeService.getAllByProductID(product.getId());

		if (listSizeProductUpdateQuatity != null) {

			for (int i = 0; i < listSizeProductUpdateQuatity.size(); i++) {
				int qtt = Integer.parseInt(listSizeProductUpdateQuatity.get(i).getQuatity());
				qtt += listSize.get(i).getQuantity();
				listSizeProductUpdateQuatity.get(i).setQuatity(String.valueOf(qtt));
			}

			for (int i = 0; i < listSize.size(); i++) {
				ProductSizeDTO2 productSize = new ProductSizeDTO2();
				productSize.setQuantity(Integer.parseInt(listSizeProductUpdateQuatity.get(i).getQuatity()));
				productSize.setUpdateAt(new Date());
				productSize.setId(listSize.get(i).getId());

				productSizeService.updateQuantity(productSize);
			}
		}
		return "redirect:/admin/products/update-form/" + product.getId();
	}

	@ResponseBody
	@PostMapping("/update/productsize")
	public void updateProductSize(@RequestBody List<ProductSizeCreateDTO> productSize) {
		sessionService.set("listSizeProductUpdate", productSize);

	}
	
	
	
	

	public boolean checkFrom(Model model, MultipartFile img, String IdProductType, String id_brand) {
		boolean check = true;
		if (img.isEmpty()) {
			model.addAttribute("message_img", "Vui lòng chọn hình ảnh");
			check = false;
		}
		if (IdProductType.equals("")) {
			model.addAttribute("message_id_producttype", "Vui lòng chọn loại sản phẩm");
			check = false;
		}
		if (id_brand.equals("")) {
			model.addAttribute("message_id_brand", "Vui lòng chọn thương hiệu sản phẩm");
			check = false;
		}
		
		
		
	
		
		return check;
	}

	@ModelAttribute("list_sizes")
	public List<Size> getListSize() {
		List<Size> list_size = new ArrayList<Size>();

		list_size = sizeService.getAllActive();

		return list_size;
	}

	@ModelAttribute("list_brands")
	public List<Brand> getListBrand() {
		List<Brand> list_brand = new ArrayList<Brand>();
		
		list_brand = brandService.getAllActive();

		return list_brand;
	}

	@ModelAttribute("list_pd_type")
	public List<ProductType> getproducType() {

		List<ProductType> list_product_type = new ArrayList<ProductType>();
		list_product_type = productTypeService.getAllActive();

		return list_product_type;
	}

}
