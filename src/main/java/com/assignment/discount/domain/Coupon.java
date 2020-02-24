package com.assignment.discount.domain;

import com.assignment.discount.enums.DiscountType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Document
public class Coupon {

    @Id
    private String id;

    @NotNull(message = "{NotNull.Coupon.minAmount}")
    private BigDecimal minAmount;

    @NotNull(message = "{NotNull.Coupon.discount}")
    private BigDecimal discount;

    @NotNull(message = "{NotNull.Coupon.discountType}")
    private DiscountType discountType;

    @Version
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}
