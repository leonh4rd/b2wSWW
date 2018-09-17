package com.leonardo.rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder= {"name", "climate", "terrain", "timesInMovie"})
public class World 
{
	private String name;
	private String climate;
	private String terrain;
	private int timesInMovie;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public int getTimesInMovie() {
		return timesInMovie;
	}
	public void setTimesInMovie(int timesInMovie) {
		this.timesInMovie = timesInMovie;
	}
	@Override
	public String toString() {
		return "World [name=" + name + ", climate=" + climate + ", terrain=" + terrain + "]" +
				 ", timesInMovie=" + timesInMovie + "]";
	}
	
}
