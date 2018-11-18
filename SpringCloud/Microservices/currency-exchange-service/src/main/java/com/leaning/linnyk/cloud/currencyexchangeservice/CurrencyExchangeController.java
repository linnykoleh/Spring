package com.leaning.linnyk.cloud.currencyexchangeservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CurrencyExchangeController {

	private final Environment environment;
	private final ExchangeValueRepository exchangeValueRepository;

	@Autowired
	public CurrencyExchangeController(Environment environment, ExchangeValueRepository exchangeValueRepository) {
		this.environment = environment;
		this.exchangeValueRepository = exchangeValueRepository;
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		final ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		final int port = Integer.parseInt(environment.getProperty("local.server.port"));
		exchangeValue.setPort(port);

        log.info("exchangeValue -> {}", exchangeValue);

		return exchangeValue;
	}
}
