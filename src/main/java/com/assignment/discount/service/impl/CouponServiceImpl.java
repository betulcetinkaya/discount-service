package com.assignment.discount.service.impl;

import com.assignment.discount.domain.Coupon;
import com.assignment.discount.exception.CouponNotFoundException;
import com.assignment.discount.repository.CouponRepository;
import com.assignment.discount.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon create(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon getById(String id) {
        Optional<Coupon> product = couponRepository.findById(id);
        if (!product.isPresent()) {
            throw new CouponNotFoundException(id);
        }
        return product.get();
    }
}

