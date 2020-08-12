package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.entity.CartItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GuestCustomerDto {
    private List<CartItem> orderItems;
    private String email;
}
