package com.leonardo.rest;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("worlds")
public class WorldResource 
{	
	List<World> worlds;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<World> GetWorlds() 
	{
		MongoClient mongo = null;
		mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("Leonardo");
		MongoCollection<Document> collection = database.getCollection("worlds");
		System.out.println(collection.countDocuments());
		mongo.close();
		
		World world1 = new World();
		world1.setName("Coruscan");
		world1.setClimate("Tropical");
		world1.setTerrain("Plain");
		
		World world2 = new World();
		world2.setName("Kamino");
		world2.setClimate("Desert");
		world2.setTerrain("Plain");
		
		worlds = Arrays.asList(world1, world2);
		return worlds;
	}
}
