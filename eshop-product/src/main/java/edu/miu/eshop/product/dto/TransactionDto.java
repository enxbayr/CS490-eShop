package edu.miu.eshop.product.dto;

import edu.miu.eshop.product.entity.Card;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDto {

    private Card card;
    private double amount;
}
