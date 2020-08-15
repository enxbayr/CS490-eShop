package edu.miu.eshop.product.entity;

import edu.miu.eshop.product.dto.CheckoutDto;
import lombok.*;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor

public class Card {
	private String cardNumber;
	private int cvv;
	private String cardHolder;
	private int month;
	private int year;
	private String billingAddress;

	public static Card build(CheckoutDto checkoutDto){
		return new Card(checkoutDto.getBankCardDto().getCardNumber(),
		Integer.parseInt(checkoutDto.getBankCardDto().getCvv()),
				checkoutDto.getBankCardDto().getHolderName(),
				checkoutDto.getBankCardDto().getExpirationDate().getMonth().getValue(),
				checkoutDto.getBankCardDto().getExpirationDate().getYear(),
		checkoutDto.getAddress().getStreet() + checkoutDto.getAddress().getCity() + checkoutDto.getAddress().getState() + checkoutDto.getAddress().getZip());
	}
}