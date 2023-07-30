package com.poly.fman.controller.user;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.fman.dto.cart.CartRequestDTO;
import com.poly.fman.dto.cart.CartResponseDTO;
import com.poly.fman.dto.cart.ChangeQuantityDTO;
import com.poly.fman.dto.cart.CheckoutReponseDTO;
import com.poly.fman.dto.model.AddressDTO;
import com.poly.fman.dto.model.AddressDTO2;
import com.poly.fman.dto.model.CartDTO;
import com.poly.fman.dto.model.CartItemDTO;
import com.poly.fman.dto.model.CartItemDTO2;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.order.CheckoutRequestDTO;
import com.poly.fman.dto.order.ReCheckoutReponseDTO;
import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.Cart;
import com.poly.fman.entity.CartItem;
import com.poly.fman.entity.Order;
import com.poly.fman.entity.OrderItem;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.repository.ProductSizeRepository;
import com.poly.fman.service.AddressService;
import com.poly.fman.service.CartService;
import com.poly.fman.service.OrderService;
import com.poly.fman.service.ProductService;
import com.poly.fman.service.common.CommonUtils;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/user/carts")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final ProductSizeRepository productSizeRepository;
    private final ModelMapper modelMapper;
    private final AddressService addressService;
    private final OrderService orderService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String cartIndex() {
        return "user/view/cart/cart-index";
    }

    @GetMapping("/cart")
    public String cart() {
        return "user/view/cart/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "user/view/cart/cart_checkout";
    }

    @PostMapping("/get-cart-items/{userId}")
    @ResponseBody
    public ResponseEntity<ResponseDTO> getCartItems(@PathVariable("userId") Integer userId,
            @RequestBody CartRequestDTO cartRequest) {
        try {
            CartResponseDTO cartResponseDTO = cartService.processCartRequest(cartRequest);
            return ResponseEntity.ok(cartResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Có lỗi xảy ra"));
        }
    }

    @GetMapping("/check-in-stock")
    @ResponseBody
    public ResponseEntity<SimpleReponseDTO> checkInStock(
            @RequestParam("productSizeId") Integer id,
            @RequestParam("quantity") int quantity) {

        // check product have enougth in inventory
        boolean rs = cartService.checkInStock(id, quantity);

        if (rs) {
            SimpleReponseDTO simpleReponseDTO = new SimpleReponseDTO("500", "Số lượng trong kho đã tối đa");
            return ResponseEntity.status(500).body(simpleReponseDTO);
        }

        return ResponseEntity.ok().build();
    }

    // @PostMapping("/checkout/{cartId}")
    // public String checkoutForm(@PathVariable("cartId") Integer cartId,
    // @RequestBody List<String> listCartItem) {
    // System.out.println("HEIIIIIII");
    // System.out.println(listCartItem.size());
    // return "user/view/cart/cart_checkout";
    // }

    @GetMapping("/checkout-bynow")
    public String buyNowForm(@RequestParam("productSizeId") Integer productSizeId,
            @RequestParam("quantity") Integer quantity,
            Model model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // check user address
        List<AddressDTO> listAddressDTOs = addressService.getByUserId((int) httpSession.getAttribute("userId"));
        System.out.println(listAddressDTOs.size());
        if (listAddressDTOs.isEmpty()) {
            model.addAttribute("hasAddress", false);
            return "user/view/cart/cart_checkout";
            // return "redirect:/user/address/all?user-id=" +
            // httpSession.getAttribute("userId");
        }

        List<CartItemDTO2> lCartItemDTOs = new ArrayList<>();
        ProductSize productSize = productSizeRepository.findById(productSizeId).orElse(null);
        Product product = productSize.getProduct();
        CartItemDTO2 cartItemDTO = new CartItemDTO2();
        cartItemDTO.setId(productSizeId);
        // cartItemDTO.setProduct(product);
        // cartItemDTO.setProductSize(productSize);
        cartItemDTO.setQuantity(quantity);
        lCartItemDTOs.add(cartItemDTO);

        int subTotal = 0;

        subTotal = product.getPrice().intValue() * quantity;

        model.addAttribute("listItem", lCartItemDTOs);
        model.addAttribute("listAddress", listAddressDTOs);
        model.addAttribute("addressDefault", listAddressDTOs.get(0));
        model.addAttribute("hasAddress", true);
        model.addAttribute("subTotal", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("totalStr", CommonUtils.convertToCurrencyString(subTotal, " VNĐ"));
        model.addAttribute("total", subTotal);
        model.addAttribute("isBuyNow", true);
        model.addAttribute("quantity", quantity);
        model.addAttribute("productSizeId", productSizeId);
        return "user/view/cart/cart_checkout";
    }

    @PostMapping("/checkout")
    @ResponseBody
    public ResponseEntity<ResponseDTO> getCheckout(@RequestBody CartRequestDTO checkoutRequestDTO) {

        try {
            CheckoutReponseDTO checkoutReponseDTO = cartService.checkout(checkoutRequestDTO);
            return ResponseEntity.ok(checkoutReponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new SimpleReponseDTO("500", "Có lỗi xảy ra"));

        }

    }


    @GetMapping("/apply-voucher")
    @ResponseBody
    public ResponseEntity<ResponseDTO> applyVoucher(
            @RequestParam("voucher") String voucherName, @RequestParam("total") Long total) {

        ResponseDTO responseDTO = cartService.applyVoucher(voucherName, total);
        if (responseDTO instanceof SimpleReponseDTO) {
            return ResponseEntity.status(404).body(responseDTO);
        } else {
            return ResponseEntity.ok(responseDTO);
        }
    }

}
