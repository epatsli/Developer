package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "FLATS")
public class FlatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	public Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFlat;

	@Column(name = "areaFlat", nullable = false)
	private Double areaFlat;

	@Column(name = "numberRooms", nullable = false)
	private Integer numberRoom;

	@Column(name = "numberBalconies")
	private Integer numberBalconie;

	@Column(name = "floor", nullable = false)
	private Integer floor;

	@Embedded
	private AddressEntity address;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idStatus", nullable = false)
	private StatusEntity flatStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Building", nullable = false)
	private BuildingEntity building;

	@Column(name = "price", nullable = false)
	private Double price;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CLIENT_BOOK_FLAT", joinColumns = @JoinColumn(name = "idClient") , inverseJoinColumns = @JoinColumn(name = "idFlat") )
	private List<ClientEntity> listClientBook = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CLIENT_BUY_FLAT", joinColumns = @JoinColumn(name = "idClient") , inverseJoinColumns = @JoinColumn(name = "idFlat") )
	private List<ClientEntity> listClientBuy = new ArrayList<>();

}
