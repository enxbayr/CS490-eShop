package edu.miu.eshop.product.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Document
public class OrderItem {
    private String productId;
    private double price;
    private int quantity;
    private String vendorId;
}
