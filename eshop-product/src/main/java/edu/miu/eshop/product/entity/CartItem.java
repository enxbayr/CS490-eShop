package edu.miu.eshop.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CartItem {

    private String productId;
    private int quantity;
    private double unitCost;
    private String vendorId;
    private String userName;

}
