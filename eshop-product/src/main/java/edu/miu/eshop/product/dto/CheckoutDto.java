package edu.miu.eshop.product.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckoutDto {
    private String customerId;
    private AddressDto address;
    private BankCardDto bankCardDto;
}
