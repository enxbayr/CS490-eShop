package edu.miu.eshop.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.miu.eshop.product.entity.CartItem;
import edu.miu.eshop.product.entity.Order;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OrderItemDto {
    private LocalDateTime orderDate;
    private String userName;
    private String customerId;
    private String productId;
    private double price;
    private int quantity;
    private String vendorId;
}