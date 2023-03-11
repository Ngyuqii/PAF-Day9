package paf.order.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import static paf.order.model.Constants.*;

import paf.order.model.Order;
import paf.order.util.*;

@Repository
public class OrderRepository {

	@Autowired
	private MongoTemplate template;

	//Method to insert a new order record
	//Convert order object to document and insert into the collection
	public void insertOrder(Order order) {
		Document doc = Utils.orderDocument(order);
		template.insert(doc, COLLECTION);
	}

}
