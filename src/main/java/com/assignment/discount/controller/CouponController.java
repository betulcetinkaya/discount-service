package com.assignment.discount.controller;

import com.assignment.discount.domain.Campaign;
import com.assignment.discount.domain.Coupon;
import com.assignment.discount.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    ResponseEntity<Coupon> create(@Valid @RequestBody Coupon coupon) {
        return new ResponseEntity<>(couponService.create(coupon), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public
    @ResponseBody
    ResponseEntity<Coupon> getById(@PathVariable("id") String id) {
        Coupon coupon = couponService.getById(id);
        return new ResponseEntity<>(coupon, HttpStatus.OK);
    }

}
