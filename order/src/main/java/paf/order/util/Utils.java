package paf.order.util;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import paf.order.model.LineItem;
import paf.order.model.Order;

public class Utils {

	//Returns JsonObject from Json String
	public static JsonObject toJson(String str) {
		Reader reader = new StringReader(str);
		JsonReader jsonReader = Json.createReader(reader);
		return jsonReader.readObject();
	}

	//Returns LineItem Object from JsonObject
	public static LineItem createLineItem(JsonObject json) {
		LineItem lineItem = new LineItem();
		lineItem.setItem(json.getString("item"));
		lineItem.setQuantity(json.getInt("quantity"));
		return lineItem;
	}

	//Returns Order Object from JsonObject
	//1. Gets value from JsonObject to set into Order Object
	//2. Unpack the jsonarray into list<object> and set list to Order Object
	public static Order createOrder(JsonObject json) {

		//1
		Order order = new Order();
		order.setName(json.getString("name"));
		order.setEmail(json.getString("email"));
		order.setDeliveryDate(new Date()); //see how to retrieve date from jsonObj

		//2
		List<LineItem> itemsList = json.getJsonArray("lineItems")
			.stream()
			.map(v -> (JsonObject)v)
			.map(v -> createLineItem(v))
			.toList();
		order.setItemsList(itemsList);

		return order;
	}

	//Returns JsonObject from LineItem Object
	public static JsonObject lineItemJson(LineItem lineItem) {
		return Json.createObjectBuilder()
			.add("item", lineItem.getItem())
			.add("quantity", lineItem.getQuantity())
			.build();
	}

	//Returns JsonObject from Order Object
	//1. Create a jsonarray from list<object>
	//2. Create a jsonobject that includes the jsonarray
	public static JsonObject orderJson(Order order) {

		//1
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		order.getItemsList()
			.stream()
			.map(v -> lineItemJson(v))
			.forEach(v -> {
				arrBuilder.add(v);
			});

		//2
		return Json.createObjectBuilder()
			.add("orderId", order.getOrderId())
			.add("name", order.getName())
			.add("email", order.getEmail())
			.add("deliveryDate", order.getDeliveryDate().toString())
			.add("lineItems", arrBuilder.build())
			.build();
	}

	//Returns Document from LineItem Object
	public static Document lineItemDocument(LineItem lineItem) {
		Document doc = new Document();
		doc.put("item", lineItem.getItem());
		doc.put("quantity", lineItem.getQuantity());
		return doc;
	}

	//Returns Document from Order Object
	public static Document orderDocument(Order order) {
		Document doc = new Document();
		doc.put("orderId", order.getOrderId());
		doc.put("name", order.getName());
		doc.put("email", order.getEmail());
		doc.put("deliveryDate", order.getDeliveryDate());

		//List of lineItems to be converted to document list docs
		//Put list docs into order doc
		List<Document> docs = order.getItemsList()
			.stream()
			.map(v -> lineItemDocument(v))
			.toList();

		doc.put("lineItems", docs);
		return doc;
	}
	
}