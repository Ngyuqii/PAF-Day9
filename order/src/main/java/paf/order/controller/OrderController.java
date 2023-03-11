package paf.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import paf.order.model.Order;
import paf.order.service.OrderService;
import paf.order.util.Utils;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	@Autowired
	private OrderService orderSvc;

	@PostMapping(path="/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> placeOrder(@RequestBody String jsonString) {

		//Check what is the received data
		System.out.printf("Payload >>> %s\n", jsonString);

		//Convert from Json String (data interchange format) to Order Object (domain entity)
		JsonObject jsonObj = Utils.toJson(jsonString);
		Order order = Utils.createOrder(jsonObj);

		//Insert the order record into collection and return orderId
		String orderId = orderSvc.insertOrder(order);

		JsonObject response = Json.createObjectBuilder()
			.add("orderId", orderId)
			.add("message", "Order placed")
			.build();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response.toString());

	}

}