package com.assignment.discount.service;

import com.assignment.discount.domain.Coupon;

public interface CouponService {

    Coupon create(Coupon coupon);

    Coupon getById(String id);
}
