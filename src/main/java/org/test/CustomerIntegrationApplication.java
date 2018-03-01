package org.test;

import hello.wsdl.GetQuoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerIntegrationApplication {

	private final Logger log = LoggerFactory.getLogger(CustomerIntegrationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerIntegrationApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(QuoteClient quoteClient) {
		return args -> {
			String ticker = "MSFT";

			if (args.length > 0) {
				ticker = args[0];
			}
			GetQuoteResponse response = quoteClient.getQuote(ticker);
			log.info(response.getGetQuoteResult());
		};
	}
}