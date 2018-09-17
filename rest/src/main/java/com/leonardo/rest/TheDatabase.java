package com.leonardo.rest;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

import static com.mongodb.client.model.Filters.*;	

public final class TheDatabase 
{
	private static TheDatabase INSTANCE = null;
	private static MongoClient mongo = null;
	private static MongoDatabase database = null;
	private static MongoCollection<Document> collection = null;
	
	private TheDatabase() 
	{
		mongo = MongoClients.create();
		database = mongo.getDatabase("Leonardo");
		collection = database.getCollection("worlds");
		collection.createIndex(Indexes.ascending("name"));
	}
	
	public static TheDatabase getInstance() 
	{
		if(INSTANCE == null) 
		{	
			INSTANCE = new TheDatabase();
		}
		return INSTANCE;
	}
	
	public List<String> FetchFirstData()
	{
		List<String> data = new ArrayList<String>();
		if(collection != null) 
		{
			Document doc;
			doc = collection.find().first();
			data.add(doc.getString("name"));
			data.add(doc.getString("climate"));
			data.add(doc.getString("terrain"));
		}
		return data;
	}
	
	public List<Document> FetchData()
	{
		List<Document> data = new ArrayList<Document>();
		if(collection != null) 
		{
			MongoCursor<Document> cursor;
			cursor = collection.find().iterator();
			while(cursor.hasNext()) 
			{
				data.add(cursor.next());
			}
		}
		return data;
	}
	
	public Document FetchDatum(String name)
	{
		Document doc = new Document();
		if(collection != null) 
		{
			doc = collection.find(eq("name", name)).first();
		}
		return doc;
	}
	
	public void AddData(World world) 
	{
		Document document = new Document("name", world.getName())
				.append("climate", world.getClimate())
				.append("terrain", world.getTerrain());
		collection.insertOne(document);
	}
	
	public void RemoveData(World world) 
	{
		collection.deleteOne(eq("name", world.getName()));
	}
	
	public void CloseDatabase() 
	{
		if(mongo != null) 
		{
			mongo.close();
		}
	}
}
