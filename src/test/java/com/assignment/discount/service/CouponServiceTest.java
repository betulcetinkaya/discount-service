package com.assignment.discount.service;

import com.assignment.discount.domain.Coupon;
import com.assignment.discount.domain.Coupon;
import com.assignment.discount.exception.CouponNotFoundException;
import com.assignment.discount.repository.CouponRepository;
import com.assignment.discount.service.impl.CouponServiceImpl;
import com.assignment.discount.testdata.CouponTestData;
import com.assignment.discount.testdata.CouponTestData;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CouponServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private CouponRepository couponRepository;

    @Autowired
    private CouponServiceImpl couponService;

    @Test
    public void testCreate_SendValidCoupon_ReturnCoupon() {
        Coupon coupon = CouponTestData.getCoupon();
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        Coupon created = couponService.create(coupon);

        verify(couponRepository).save(any(Coupon.class));
        Assert.assertEquals(coupon.getId(), created.getId());
        Assert.assertEquals(coupon.getMinAmount(), created.getMinAmount());
        Assert.assertEquals(coupon.getDiscount(), created.getDiscount());
    }

    @Test
    public void testGetById_RecordNotFound_ThrowException() {
        String id = "PRODUCT-001";
        when(couponRepository.findById(anyString())).thenReturn(Optional.empty());
        thrown.expect(CouponNotFoundException.class);
        thrown.expectMessage(id);

        couponService.getById(id);

        verify(couponRepository).findById(anyString());
    }

    @Test
    public void testGetBy_SendExistingId_ReturnCoupon() {
        Coupon coupon = CouponTestData.getCoupon();
        when(couponRepository.findById(anyString())).thenReturn(Optional.of(coupon));

        Coupon found = couponService.getById(coupon.getId());

        verify(couponRepository).findById(anyString());
        Assert.assertEquals(coupon.getId(), found.getId());
        Assert.assertEquals(coupon.getDiscount(), found.getDiscount());
        Assert.assertEquals(coupon.getMinAmount(), found.getMinAmount());
        Assert.assertEquals(coupon.getDiscountType(), found.getDiscountType());
    }


}
