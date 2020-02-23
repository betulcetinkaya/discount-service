package com.assignment.discount.controller;

import com.assignment.discount.ControllerBaseTest;
import com.assignment.discount.domain.Campaign;
import com.assignment.discount.domain.Coupon;
import com.assignment.discount.service.CouponService;
import com.assignment.discount.testdata.CampaignTestData;
import com.assignment.discount.testdata.CouponTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponController.class)
public class CouponControllerTest extends ControllerBaseTest {
    @MockBean
    private CouponService couponService;

    @BeforeClass
    public static void init() {
        baseAddress = "/coupons";
    }

    @Test
    public void testCreateCoupon_SendValidRequest_ReturnCreated() throws Exception {
        Coupon coupon = CouponTestData.getCoupon();
        when(couponService.create(any(Coupon.class))).thenReturn(coupon);

        ResultActions perform = mockMvc.perform(post(baseAddress)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(coupon)));


        perform.andExpect(status().isCreated());
    }

    @Test
    public void testGetCoupon_SendId_GetACoupon() throws Exception {
        Coupon coupon = CouponTestData.getCoupon();
        when(couponService.getById(coupon.getId())).thenReturn(coupon);

        ResultActions resultActions = mockMvc.perform(get(baseAddress + "/" + coupon.getId())
                .contentType(mediaType));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(coupon.getId())));

        verify(couponService).getById(anyString());
    }


}
