package com.example.payment.adapter.inbound.rest;

import com.example.payment.domain.AddPaymentCommand;
import com.example.payment.domain.model.Iban;
import com.example.payment.domain.model.Money;
import com.example.payment.domain.model.UserId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Currency;

@Value
public class AddPaymentRequest {
    @NotNull
    private final String userId;

    @ApiModelProperty(value = "Amount", example = "100.55")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private final BigDecimal amount;

    @ApiModelProperty(value = "ISO 4217 currency code", example = "USD")
    @NotNull
    @Size(min = 3, max = 3)
    private final String currency;

    @ApiModelProperty(value = "IBAN", example = "PL82102052260000610204177895")
    @NotNull
    private final String accountNumber;

    AddPaymentCommand toCommand() {
        return AddPaymentCommand.builder()
                .userId(UserId.of(userId))
                .amount(Money.of(amount, Currency.getInstance(currency)))
                .accountNumber(Iban.of(accountNumber))
                .build();
    }
}
