package com.vodafone.demo.service;

import com.vodafone.demo.exceptions.NotFoundException;
import com.vodafone.demo.model.CtnDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CtnService.class)
public class ctnServiceTest {
    @Autowired
    private CtnService ctnService;

    @Test
    public void getCtnDetailsTestSuccess_withValidCtn_returnCtnDetails(){
        CtnDetails ctnDetails = ctnService.getDetails("07624367524");

        assertEquals("07624367524",ctnDetails.getIdentifier());
        assertEquals("paris",ctnDetails.getAddress());
        assertEquals("john",ctnDetails.getCustomerName());
        assertEquals("payg",ctnDetails.getSubscriptionType());

    }

    @Test
    public void getCtnDetailsTestFailure_withValidCtnNotExist_returnNotFoundException(){
        assertThrows(NotFoundException.class,()->{ctnService.getDetails("07624367879");});
    }

}
