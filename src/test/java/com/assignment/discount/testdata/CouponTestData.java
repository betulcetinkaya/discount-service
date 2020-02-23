package com.assignment.discount.testdata;

import com.assignment.discount.domain.Coupon;
import com.assignment.discount.enums.DiscountType;

import java.math.BigDecimal;

public class CouponTestData {

    public static Coupon getCoupon() {
        Coupon coupon = new Coupon();
        coupon.setId("COUPON-001");
        coupon.setMinAmount(new BigDecimal(200));
        coupon.setDiscount(new BigDecimal(100));
        coupon.setDiscountType(DiscountType.AMOUNT);
        return coupon;
    }
}
