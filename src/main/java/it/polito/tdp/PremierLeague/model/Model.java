package it.polito.tdp.PremierLeague.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao; 
	List<Player> vertici; 
	List<Action> archi;
	Graph <Player, DefaultWeightedEdge> grafo; 
	Map<Integer, Player> idMap; 
	
	
	public Model() {
		this.dao= new PremierLeagueDAO(); 
	}
	
	public String creaGrafo(double goal) {
		this.grafo= new DefaultDirectedWeightedGraph <>(DefaultWeightedEdge.class); 
		idMap= new HashMap<Integer, Player>(); 
		vertici=dao.listAllPlayers(goal, idMap);
		archi= dao.listAllActions();
		Graphs.addAllVertices(this.grafo,vertici);

    	// non so se utile Collections.sort(vertici);
    	Collections.sort(archi);
		for (Player p: vertici) {
			for(Player p1: vertici) {
				if(grafo.containsVertex(p) && grafo.containsVertex(p1) 
						&& p!=p1) {
					for(Action a: archi) {
						for (Action a1: archi) {
							if(a.teamID!= a1.teamID && 
								a.starts==a1.starts && a.starts==1
								&& a.matchID==a1.matchID) {
								double peso=Math.abs(a.timePlayed-a1.timePlayed);
								if(peso>=0) {
									Graphs.addEdge(this.grafo, p, p1, peso);
								}else 
									Graphs.addEdge(this.grafo, p1, p, (-1)*peso);
							}
						}
					}
				}
			}
		}
		return "Grafo creato!\n# Vertici:"+grafo.vertexSet().size()+ "\n# Archi: "+grafo.edgeSet().size();	
	}
	
	
}
