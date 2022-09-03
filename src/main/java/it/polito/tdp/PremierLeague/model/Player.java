package it.polito.tdp.PremierLeague.model;

import java.util.Objects;

public class Player implements Comparable<Player>{
	Integer playerID;
	String name;
	Integer t; 
	
	public Player(Integer playerID, String name, Integer t) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.t= t; 
	}
	
	public Integer getPlayerID() {
		return playerID;
	}
	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	public Integer getT() {
		return t;
	}

	public void setT(Integer t) {
		this.t = t;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, playerID, t);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name) && Objects.equals(playerID, other.playerID)
				&& Objects.equals(t, other.t);
	}

	@Override
	public String toString() {
		return playerID + " - " + name + " - " + t;
	}

	@Override
	public int compareTo(Player o) {
		return -(this.t-o.t); //ordine decrescente del tempo
	}

	
	
	
}
