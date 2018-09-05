package com.guitar.db.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NamedNativeQuery(name = "Manufacturer.getAllThatSellAcoustics", 
		query = "SELECT m.id, m.name, m.foundedDate, m.averageYearlySales, m.location_id as headquarters_id "
	    + "FROM Manufacturer m "
		+ "LEFT JOIN Model mod ON (m.id = mod.manufacturer_id) "
		+ "LEFT JOIN ModelType mt ON (mt.id = mod.modeltype_id) "
	    + "WHERE (mt.name = ?)", resultClass = Manufacturer.class)
public class Manufacturer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	private Date foundedDate;
	private BigDecimal averageYearlySales;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="MANUFACTURER_ID")
	private List<Model> models = new ArrayList<>();

	@ManyToOne
	private Location headquarters;

	public String getName() {
		return name;
	}
}
