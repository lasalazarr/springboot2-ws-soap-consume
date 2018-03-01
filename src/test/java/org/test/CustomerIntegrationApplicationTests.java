package org.test;

import hello.wsdl.GetQuoteResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerIntegrationApplicationTests {

	@Autowired
	QuoteClient quoteClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void quoteClientTest(){
		// given
		String ticker = "exception";

		// when
		GetQuoteResponse response = quoteClient.getQuote(ticker);

		// then
		assertThat(response.getGetQuoteResult())
				.isEqualTo(ticker);
	}
}