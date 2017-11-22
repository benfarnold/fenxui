package org.fenxui.data.mongo.service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.fenxui.application.event.BroadcastEvent;
import org.fenxui.data.service.DataService;

public class MongoDataService implements DataService<Document, String> {
	private final MongoClient mongoClient;
	private final MongoDatabase database;

	public MongoDataService(MongoClient mongoClient, String name) {
		this.mongoClient = mongoClient;
		database = mongoClient.getDatabase(name);
	}

	@Override
	public void register(String tablePropertyName) {
		//no-op
	}

	@Override
	public void handleEvent(BroadcastEvent sampleEvent) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Document> findAll(String tablePropertyName, Bson queryObject) {
		MongoCollection mongoCollection = database.getCollection(tablePropertyName);
		return (List<Document>) mongoCollection.find(queryObject).into(new ArrayList<>());
	}

	@Override
	public Document upsert(String tablePropertyName, Map<String, Object> toUpsert) {
		Document document = buildDocument(toUpsert);
		MongoCollection mongoCollection = database.getCollection(tablePropertyName);
		mongoCollection.insertOne(document);
		return document;
	}

	private Document buildDocument(Map<String, Object> map) {
		Document document = new Document();
		for (String key : map.keySet()) {
			Object o = map.get(key);
			if (o instanceof Map) {
				Document d = buildDocument((Map<String, Object>) o);
				document.append(key, d);
			} else {
				document.append(key, o);
			}
		}
		return document;
	}

	@Override
	public List<Document> findAll(String tablePropertyName, Q queryObject) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
