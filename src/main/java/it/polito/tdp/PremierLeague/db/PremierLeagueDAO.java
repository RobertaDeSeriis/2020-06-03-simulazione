package it.polito.tdp.PremierLeague.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.PremierLeague.model.Action;
import it.polito.tdp.PremierLeague.model.Player;

public class PremierLeagueDAO {
	
	public List<Player> listAllPlayers(double goal, Map<Integer, Player> idMap){ //almeno x goal in media a partita
		String sql = "SELECT p.PlayerID, p.Name, sum (a.TimePlayed) as t" 
				+ "FROM players p, actions a "
				+ "WHERE a.PlayerID=p.PlayerID "
				+ "GROUP BY p.PlayerID, p.Name "
				+ "HAVING AVG(a.Goals)>? ";
		List<Player> result = new ArrayList<Player>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, goal);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(!idMap.containsKey(res.getInt("id"))) {
				Player player = new Player(res.getInt("PlayerID"), res.getString("Name"), res.getInt("t"));
				idMap.put(player.getPlayerID(), player); //riempimento idMap
				result.add(player);
			}}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Action> listAllActions(){
		String sql = "SELECT * FROM Actions";
		List<Action> result = new ArrayList<Action>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Action action = new Action(res.getInt("PlayerID"),res.getInt("MatchID"),res.getInt("TeamID"),res.getInt("Starts"),res.getInt("Goals"),
						res.getInt("TimePlayed"),res.getInt("RedCards"),res.getInt("YellowCards"),res.getInt("TotalSuccessfulPassesAll"),res.getInt("totalUnsuccessfulPassesAll"),
						res.getInt("Assists"),res.getInt("TotalFoulsConceded"),res.getInt("Offsides"));
				
				result.add(action);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/* public List<Adiacenza> Archi(int star, double goal, Map<Integer, Player> idMap){ //almeno x goal in media a partita
		String sql = "SELECT a1.PlayerID AS p1, a2.PlayerID AS p2, a1.TimePlayed AS t1, a2.TimePlayed AS t2 "
				+ "FROM actions a1, actions a2 "
				+ "WHERE a1.PlayerID<>a2.PlayerID "
				+ "AND a1.TeamID<>a2.TeamID "
				+ "AND a1.MatchID=a2.MatchID "
				+ "AND a1.`Starts`=a2.`Starts` "
				+ "AND a1.`Starts`=? "
				+ "GROUP BY p1, p2 "
				+ "ORDER BY t1 desc,t2 ";
		List<Player> result = new ArrayList<Player>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, star);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(idMap.containsKey(res.getInt("p1")) && idMap.containsKey(res.getInt("p2"))) {
				
				Adiacenza a = new Adiacenza(res.getInt("PlayerID"), res.getString("Name"));
				idMap.put(player.getPlayerID(), player); //riempimento idMap
				result.add(player);
			}}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
