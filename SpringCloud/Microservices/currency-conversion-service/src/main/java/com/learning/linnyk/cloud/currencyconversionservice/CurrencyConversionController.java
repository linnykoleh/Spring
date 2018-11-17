package com.learning.linnyk.cloud.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        final ResponseEntity<CurrencyConversionBean> responseEntity
                = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
        final CurrencyConversionBean response = responseEntity.getBody();

        final BigDecimal conversionMultiple = response.getConversionMultiple();
        return new CurrencyConversionBean(response.getId(), from, to, conversionMultiple, quantity, quantity.multiply(conversionMultiple), response.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        final CurrencyConversionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
        final BigDecimal conversionMultiple = response.getConversionMultiple();
        return new CurrencyConversionBean(response.getId(), from, to, conversionMultiple, quantity, quantity.multiply(conversionMultiple), response.getPort());
    }
}
