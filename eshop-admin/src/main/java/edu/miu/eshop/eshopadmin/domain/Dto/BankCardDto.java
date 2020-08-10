package edu.miu.eshop.eshopadmin.domain.Dto;

// EB

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankCardDto {
    private String cardNumber;
    private String holderName;
    private String bankName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate expirationDate;
    private String cvv;
    private boolean cardStatus = true;
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof BankCardDto))
            return false;
        BankCardDto b = (BankCardDto) o;
        if(b.getCardNumber() != this.cardNumber)
            return false;
        return true;
    }
    public int hashCode(){
        return Objects.hash(this.cardNumber);
    }
}
