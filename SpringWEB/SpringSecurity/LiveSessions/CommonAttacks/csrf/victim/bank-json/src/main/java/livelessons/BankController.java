package livelessons;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * @author Rob Winch
 * @since 5.1
 */
@RestController
public class BankController {

	private final ObjectMapper mapper;

	private double balance = 100.00;

	public BankController(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@GetMapping("/balance")
	Map<String, Object> index() {
		return Collections.singletonMap("balance", this.balance);
	}

	@PostMapping("/transfer")
	Map<String, Object> transfer(HttpServletRequest request) throws IOException {
		TransferRequest transfer =
				this.mapper.readValue(request.getInputStream(), TransferRequest.class);
		this.balance -= transfer.getAmount();
		return index();
	}

	static class TransferRequest {
		private double amount;

		public double getAmount() {
			return this.amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}
	}
}
