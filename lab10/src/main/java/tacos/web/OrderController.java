package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

	@GetMapping("/orders/current")
	public String orderForm() {
		return "orderForm";
	}
}
