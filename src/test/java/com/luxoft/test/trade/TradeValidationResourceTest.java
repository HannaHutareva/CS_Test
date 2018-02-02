package com.luxoft.test.trade;

import com.luxoft.test.trade.model.Trade;
import com.luxoft.test.trade.service.TradeValidationService;
import com.luxoft.test.trade.validation.ValidationResults;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TradeValidationResource.class)
public class TradeValidationResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TradeValidationService tradeValidationService;

    private final String TEST_FILE_NAME = "example.json";
    private final String PATH = "/trades/validation";

    @Test
    public void testOK() throws Exception {
        List<Trade> list = new ArrayList<>();
        ValidationResults validationResults = new ValidationResults();
        given(this.tradeValidationService.validate(list))
                .willReturn(validationResults);
        this.mvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(readTestJson()))
                .andExpect(status().isOk());
    }

    @Test
    public void testFail() throws Exception {
        when(this.tradeValidationService.validate(anyList())).thenThrow(IllegalArgumentException.class);
        this.mvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(readTestJson()))
                .andExpect(status().isBadRequest());
    }

    private String readTestJson() {
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(TEST_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
