package com.poly.fman.controller.admin;

import com.poly.fman.dto.model.BrandDTO;
import com.poly.fman.dto.model.ProductDTO;
import com.poly.fman.dto.model.ProductDTO2;
import com.poly.fman.dto.model.ProductSizeCreateDTO;
import com.poly.fman.dto.model.ProductSizeDTO;
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


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@CrossOrigin("*")
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

	@GetMapping("/list") 
	@ResponseBody
	public ResponseEntity<List<Product>> getAllProduct() {
		return ResponseEntity.ok(productService.getAllActive());	
	}


	@GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        if (!productService.existProductById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.getById(id));
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
        if (!productService.existProductById(id)) {
            return ResponseEntity.notFound().build();
        }
		try {
			  productService.delete(id);
		} catch (Exception e) {
			System.out.println(e);
		}
      
        return ResponseEntity.ok().build();
    }
	

	  @GetMapping("/create")
   	  public String createFormProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
		try {
			 List<Brand> listBrand = new ArrayList<Brand>();
			 listBrand = brandService.getAllActive();
		     List<ProductType> listProductTypes = new ArrayList<ProductType>();
			 listProductTypes = productTypeService.getAllActive();
			 List<Size> listSises = new ArrayList<Size>();
			  listSises = sizeService.getAllActive();
		model.addAttribute("listSizes", listSises);
		model.addAttribute("listProductTypes", listProductTypes);
		model.addAttribute("listBrands", listBrand);
		} catch (Exception e) {
			System.out.println(e);
		}
		
        return "admin/layout/Product/product-add";
    	}


		@PostMapping("/create")
		public ResponseEntity<ProductDTO2> create(@RequestParam("photo_file") MultipartFile photo_file,
													   @RequestParam("product_id") String product_id,
													   @RequestParam("product_name") String product_name,
													    @RequestParam("brandId") String brandId,
													   @RequestParam("productTypeId") String productTypeId,
													    @RequestParam("price") String price,
														@RequestParam("desc") String desc	   
													   ) {
	   try {
				//   Save file ảnh vào thư mục images
				paramService.saveSpringBootUpdated(photo_file, "src\\main\\resources\\static\\admin-resouce\\plugins\\images");
	
				// Thêm phương thức thanh toán vào cơ sở dữ liệu
				ProductDTO2 productDTO2 = new ProductDTO2();
				
				productDTO2.setId(product_id);
				productDTO2.setName(product_name);
			 
				productDTO2.setImage(photo_file.getOriginalFilename());
				
				Brand brand = brandService.getById(brandId);
				ProductType productType = productTypeService.getById(productTypeId);
				productDTO2.setCreateAt(new Date());
				productDTO2.setBrand(brand);
				productDTO2.setProductType(productType);
				productDTO2.setActive((byte) 1);
				int priceString = Integer.parseInt(price);
				BigInteger priceNew = BigInteger.valueOf(priceString);
				productDTO2.setPrice(priceNew);
				if (desc.equals("")) {
					productDTO2.setDesc(" ");
				}else {
					productDTO2.setDesc(desc);
				}
				
				// Kiểm tra xem phương thức thanh toán đã tồn tại hay chưa
				if (!productService.existProductById(product_id)) {
					ResponseEntity.notFound().build();
				}
				Product productthuong = modelMapper.map(productDTO2, Product.class);
				productService.addProduct(productthuong);
				
				List<ProductSizeCreateDTO> listSizeProduct = new ArrayList<ProductSizeCreateDTO>();
				listSizeProduct = sessionService.get("listSizeProductNew");

				for (int i = 0; i < listSizeProduct.size(); i++) {
					if (listSizeProduct.get(i).getQuatity().equals("")) {
						continue;
					}else {
							ProductSizeDTO productSize = new ProductSizeDTO();
							productSize.setId(i);
							productSize.setActive((byte) 1);
							productSize.setProduct(productthuong);
							productSize.setQuantity(Integer.parseInt(listSizeProduct.get(i).getQuatity()));
							productSize.setCreateAt(new Date());
							productSize.setSize(sizeService.getById(listSizeProduct.get(i).getId()));
							productSizeService.create(productSize);
					}
			   		 sessionService.remove("listSizeProductNew");
		}
		
	
				// Trả về đối tượng phương thức thanh toán đã được thêm
				 sessionService.remove("listSizeProductNew");
				return ResponseEntity.ok(productDTO2);
			}catch (Exception e){
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	
		@ResponseBody
		@PostMapping("/create/productsize")
		public void createProductSize(@RequestBody List<ProductSizeCreateDTO> productSize) {
			sessionService.set("listSizeProductNew", productSize);
		}


		@ResponseBody
		@PostMapping("/create/productsizeupdate")
		public void sizeListUpdate(@RequestBody List<ProductSizeCreateDTO> productSizeUpdate) {
			sessionService.set("listSizeUpdate", productSizeUpdate);
		}



		
		@GetMapping("/update-form")
		public String updateForm(Model model) {
				try {
			 List<Brand> listBrand = new ArrayList<Brand>();
			 listBrand = brandService.getAllActive();
		     List<ProductType> listProductTypes = new ArrayList<ProductType>();
			 listProductTypes = productTypeService.getAllActive();
			 List<Size> listSises = new ArrayList<Size>();
			  listSises = sizeService.getAllActive();
		model.addAttribute("listSizes", listSises);
		model.addAttribute("listProductTypes", listProductTypes);
		model.addAttribute("listBrands", listBrand);
		} catch (Exception e) {
			System.out.println(e);
		}
			return "admin/layout/Product/product-update";
		}
	
		@PutMapping("/{id}")
		public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, 
													  @RequestParam("photo_file") MultipartFile photo_file,
													   @RequestParam("product_name") String product_name,
													    @RequestParam("brandId") String brandId,
													   @RequestParam("productTypeId") String productTypeId,
													    @RequestParam("price") String price,
														@RequestParam("desc") String desc) {
			if (!productService.existProductById(id)) {
				ResponseEntity.notFound().build();
			}
				//   Save file ảnh vào thư mục images
				paramService.saveSpringBootUpdated(photo_file, "src\\main\\resources\\static\\admin-resouce\\plugins\\images");
	
				


		// lấy product trước khi update
		Product productHtr = productService.getById(id);
		ProductDTO2 productDTO = new ProductDTO2();
		// tạo product history
			ProductHistory productHistory = new ProductHistory();
			productHistory.setPrice(productHtr.getPrice());
			productHistory.setProduct(productHtr);
			productHistory.setCreateAt(new Date());
			// thêm product history vào bảng
			productHistoryService.create(productHistory);
		//


		// set du lieu productDTO
		productDTO.setId(id);
		productDTO.setName(product_name);

		ProductType prdtype = new ProductType();
		prdtype = productTypeService.getById(productTypeId);
		productDTO.setProductType(prdtype);
		Brand brand = new Brand();
		brand = brandService.getById(brandId);
		productDTO.setBrand(brand);

		productDTO.setDesc(desc);
		productDTO.setUpdateAt(new Date());
		productDTO.setImage(photo_file.getOriginalFilename());

		int priceString = Integer.parseInt(price);
				BigInteger priceNew = BigInteger.valueOf(priceString);
				productDTO.setPrice(priceNew);
				productDTO.setDesc(desc);



		Product productThuong = modelMapper.map(productDTO, Product.class);
		// update producte thành công
		productService.updateProduct(productThuong);
		


		// update product size
		List<ProductSizeCreateDTO> listSizeProductUpdateQuatity = new ArrayList<ProductSizeCreateDTO>();
		listSizeProductUpdateQuatity = sessionService.get("listSizeUpdate");
		List<ProductSize> listSize = new ArrayList<ProductSize>();
		listSize = productSizeService.getAllByProductID(id);
		int sizeLists = listSizeProductUpdateQuatity.size();
		System.out.println(listSize.size());

		// remove nhung thang ko co quantity
		for (int i = 0; i < sizeLists; i++) {
			if (listSizeProductUpdateQuatity.get(i).getQuatity().equals("")) {
				listSizeProductUpdateQuatity.remove(i);
				sizeLists = listSizeProductUpdateQuatity.size();
				i = 0 ;
			}
		}

		System.out.println("Size list update : "+listSizeProductUpdateQuatity.size());
		for (int index = 0; index < listSizeProductUpdateQuatity.size(); index++) {
			System.out.println("Size id : "+listSizeProductUpdateQuatity.get(index).getId());
						System.out.println("Size quantity : "+listSizeProductUpdateQuatity.get(index).getQuatity());

		}


		// phan loai size da ton tai va size moi them vao
		
		int sizeCheck = listSizeProductUpdateQuatity.size();
		if (listSize.size()>0) {
		for (int i = 0; i < sizeCheck; i++) {
			for (int j = 0; j < listSize.size(); j++) {
				if (listSizeProductUpdateQuatity.get(i).getId().equals(listSize.get(j).getSize().getId())) {
						ProductSizeDTO2 productSize = new ProductSizeDTO2();
						productSize.setQuantity(Integer.parseInt(listSizeProductUpdateQuatity.get(i).getQuatity()));
						productSize.setUpdateAt(new Date());
					    productSize.setId(listSize.get(i).getId());
				        productSizeService.updateQuantity(productSize);
						listSize.remove(j);
						listSizeProductUpdateQuatity.remove(i);
						sizeCheck = listSizeProductUpdateQuatity.size();
						i = -1;
				       
			}
			}
			
		}
		}else {
			for (int i = 0; i < listSizeProductUpdateQuatity.size(); i++) {		
						ProductSizeDTO productSize = new ProductSizeDTO();
						productSize.setId(i);
						productSize.setCreateAt(new Date());
						productSize.setQuantity(Integer.parseInt(listSizeProductUpdateQuatity.get(i).getQuatity()));
						productSize.setActive((byte) 1);
						productSize.setProduct(productThuong);
						productSize.setSize(sizeService.getById(listSizeProductUpdateQuatity.get(i).getId()));
						productSizeService.create(productSize);				
				}
				sessionService.remove("listSizeUpdate");
				return ResponseEntity.ok(productThuong);
		}


		if (listSizeProductUpdateQuatity.size() > 0) {
			for (int i = 0; i < listSizeProductUpdateQuatity.size(); i++) {		
						ProductSizeDTO productSize = new ProductSizeDTO();
						productSize.setId(i);
						productSize.setCreateAt(new Date());
						productSize.setQuantity(Integer.parseInt(listSizeProductUpdateQuatity.get(i).getQuatity()));
						productSize.setActive((byte) 1);
						productSize.setProduct(productThuong);
						productSize.setSize(sizeService.getById(listSizeProductUpdateQuatity.get(i).getId()));
						productSizeService.create(productSize);				
				}
		}
			
			sessionService.remove("listSizeUpdate");		
			return ResponseEntity.ok(productThuong);
		}
	
}
