package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.dto.CheckoutDto;
import edu.miu.eshop.product.dto.GuestCustomerDto;
import edu.miu.eshop.product.dto.OrderItemDto;
import edu.miu.eshop.product.dto.UserNameDto;
import edu.miu.eshop.product.entity.Order;
import edu.miu.eshop.product.entity.Customer;
import edu.miu.eshop.product.entity.ShoppingCart;
import edu.miu.eshop.product.service.OrderService;
import edu.miu.eshop.product.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    // ORDER CREATE
    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody UserNameDto userNameDto){
        ShoppingCart cart = shoppingCartService.findCartForUser(userNameDto.getUserName());
        System.out.println(cart);
        if(cart==null )  {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user has no order");
        }
        orderService.createOrder(cart, userNameDto.getUserName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Order created");
    }

    //READ
    @GetMapping("/{customerId}")
    public ResponseEntity getAllOrders(@PathVariable String customerId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body( orderService.getAllOrders(customerId));
    }

    @GetMapping("/order/{orderNumber}")
    public Order getOrder(@PathVariable("orderNumber") String orderNumber){
        return orderService.getOrder(orderNumber);
    }

    @GetMapping("/orderitems/{vendorId}")
    public List<OrderItemDto> getOrderByVendor(@PathVariable("vendorId") String vendorId){
        return orderService.getOrderByVendor(vendorId);
    }

    //CHECKOUT and CONFIRM PAYMENT
    @PostMapping("/checkout")
    public ResponseEntity checkout(@RequestBody CheckoutDto checkoutDto){
        orderService.checkout(checkoutDto);
        return ResponseEntity.ok().body("Order checkout is successfully.");
    }

    @PostMapping("/guestcheckout")
    public ResponseEntity<?> guestCheckout(@RequestBody GuestCustomerDto guestCustomerDto){

        orderService.createGuestOrder(guestCustomerDto.getOrderItems(), guestCustomerDto.getEmail());
        return ResponseEntity.ok().body("Guest Order checkout is successfully.");
    }

    //UPDATE ORDER
    @PatchMapping("/update/{orderNumber}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderNumber") String orderNumber,@RequestBody @Valid Order newOrder){

        Order oldOrder = orderService.getOrder(orderNumber);
        oldOrder.setUserName(newOrder.getUserName());
        oldOrder.setOrderDate(newOrder.getOrderDate());
        oldOrder.setTotalCost(newOrder.getTotalCost());
        oldOrder.setOrderItems(newOrder.getOrderItems());
        orderService.updateOrder(oldOrder);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Order updated.");
    }

    //DELETE ORDER
    @DeleteMapping("/delete/{orderNumber}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderNumber") String orderNumber){

        orderService.deleteOrder(orderNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Order deleted successfully.");
    }

    //INVOICE GENERATION
    @PostMapping("/invoice/{orderNumber}")
    public String generateInvoice(@RequestBody  @Valid  Customer customer, @PathVariable("orderNumber") String  orderNumber) {

        /*
        CALL TO REPORT MODULE
         */
        // reportModule.generateInvoice(customer,orderNumber, TaxRate.TAX_RATE.getStateTax());
        return "Invoice Generated";
    }
}