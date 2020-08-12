package edu.miu.eshop.product.entity;

import edu.miu.eshop.product.constants.CustomerStatus;
import edu.miu.eshop.product.constants.Role;
import edu.miu.eshop.product.dto.AddressDto;
import edu.miu.eshop.product.dto.BankCardDto;
import lombok.*;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Customer {
    private String id;
    private String customerId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate createdDate;
    private String phone;
    private Set<BankCardDto> cards = new HashSet<>();
    private CustomerStatus status = CustomerStatus.ACTIVE;
    private Role role = Role.ROLE_CUSTOMER;
    private String imageUrl;
    private double totalScore = 0.0;
    private AddressDto billingAddress;
    private AddressDto shippingAddress;

    public void addScore(double score){
        this.totalScore += score;
    }
    public void addCard(BankCardDto newCard){
        this.cards.add(newCard);
    }

    public void deleteCard(BankCardDto bankCardDto) {
        this.cards.remove(bankCardDto);
    }
}