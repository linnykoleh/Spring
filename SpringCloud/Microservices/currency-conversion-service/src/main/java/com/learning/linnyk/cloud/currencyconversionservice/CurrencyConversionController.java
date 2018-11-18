package com.learning.linnyk.cloud.currencyconversionservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;


    /**
     * Uses old direct call to microservice by RestTemplate
     */
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

    /**
     * 1. Uses Feign, by name of the microservice look up it from Discovery Server and makes calls
     * 2. Uses Ribbon for load balance requests and not hard coded url.
     */
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        final CurrencyConversionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        log.info("response -> {}", response);

        final BigDecimal conversionMultiple = response.getConversionMultiple();
        return new CurrencyConversionBean(response.getId(), from, to, conversionMultiple, quantity, quantity.multiply(conversionMultiple), response.getPort());
    }
}
