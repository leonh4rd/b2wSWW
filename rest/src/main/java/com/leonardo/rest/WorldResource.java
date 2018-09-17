package com.leonardo.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("planets")
public class WorldResource 
{	
	List<World> worlds = new ArrayList<World>();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<World> GetWorlds() 
	{
		worlds.clear();
		TheDatabase db = TheDatabase.getInstance();
		List<Document> entries = db.FetchData();
		for(int k = 0; k < entries.size(); k++)
		{
			World w = new World();
			w.setName(entries.get(k).getString("name"));
			w.setClimate(entries.get(k).getString("climate"));
			w.setTerrain(entries.get(k).getString("terrain"));
			worlds.add(w);
		}
		return worlds;
	}
	
	@GET
	@Path("{name}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public World GetWorld(@PathParam("name")String name) 
	{
		worlds.clear();
		TheDatabase db = TheDatabase.getInstance();
		Document doc = db.FetchDatum(name);
		World w = new World();
		w.setName(doc.getString("name"));
		w.setClimate(doc.getString("climate"));
		w.setTerrain(doc.getString("terrain"));
		return w;
	}
	
	@GET
	@Path("1")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<World> GetFirstWorld() 
	{
		worlds.clear();
		TheDatabase db = TheDatabase.getInstance();
		List<String> list = db.FetchFirstData();
		for(int k = 0; k < list.size(); k+=3)
		{
			World w = new World();
			w.setName(list.get(k));
			w.setClimate(list.get(k+1));
			w.setTerrain(list.get(k+2));
			worlds.add(w);
		}
		return worlds;
	}
	
	@POST
	@Path("insert")
	public World AddWorld(World w) 
	{
		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		String planet_name = w.getName();
		System.out.println(planet_name);
		try 
		{
			URL url = new URL("https://swapi.co/api/planets?search=" + planet_name);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("User-Agent", "Mozilla/4.0");
			BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()) );
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) 
			{
			    content.append(inputLine);
			}
			in.close();
			System.out.println(content.toString());
			jObject = new JSONObject(content.toString());
			jArray = jObject.getJSONArray("films");
			con.disconnect();
		}catch(Exception e) 
		{
			System.out.println(e);
		}
		
		TheDatabase db = TheDatabase.getInstance();
		w.setTimesInMovie(jArray.length());
		db.AddData(w);
		return w;
	}
	
	@DELETE
	@Path("{name}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public World DeleteWorld(@PathParam("name")String name)
	{
		TheDatabase db = TheDatabase.getInstance();
		Document doc = db.FetchDatum(name);
		World w = new World();
		w.setName(doc.getString("name"));
		w.setClimate(doc.getString("climate"));
		w.setTerrain(doc.getString("terrain"));
		db.RemoveData(w);
		return w;
	}
}
