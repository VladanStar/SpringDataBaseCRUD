package com.vladancupric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offersDao")
public class OffersDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource (DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	//get all offers from db
	public List<Offer> getOffers() {
		return jdbc.query("SELECT * FROM offers", new RowMapper<Offer>(){

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
			
		});
	}
	public Offer getOfferbyId(int id) {
		//SELECT * FROM offers WHERE id= :id
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.queryForObject("SELECT * FROM offers WHERE id = :id", params, new RowMapper<Offer>() {
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Offer offer = new Offer();
			offer.setId(rs.getInt("id"));
			offer.setName(rs.getString("name"));
			offer.setEmail(rs.getString("email"));
			offer.setText(rs.getString("text"));
			
			return offer;
		} 
		});
		
		
	}
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.update("DELETE FROM offers WHERE id = :id", params) ==1;
		
		} 
	
	/*public boolean createOffer(Offer offer) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", offer.getName());
		params.addValue("email", offer.getEmail());
		params.addValue("text", offer.getTekst());
	}*/
	/* boolean createOffer(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("INSERT INTO offers (id, name, email, text) VALUES(:id, :name, :email, :text)", params)==1;
	}*/
	public boolean create (Offer offer){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("INSERT INTO offers (id, name, text, email) VALUES (:id, :name, :text, :email)", params) == 1;
	}
	

}	
		
	


 