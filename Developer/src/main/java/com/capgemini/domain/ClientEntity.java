package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "CLIENTS")
public class ClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	public Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idClient;

	@Column(name = "firstName", length = 16, nullable = false)
	private String firstName;

	@Column(name = "lastName", length = 16, nullable = false)
	private String lastName;

	@Embedded
	private AddressEntity address;

	@Column(name = "phoneNumber", length = 16, nullable = false)
	private String phoneNumber;

	@ManyToMany(mappedBy = "listClientBook", cascade = CascadeType.ALL)
	private List<FlatEntity> listBookFlat = new ArrayList<>();

	@ManyToMany(mappedBy = "listClientBuy", cascade = CascadeType.ALL)
	private List<FlatEntity> listBuyFlat = new ArrayList<>();

}
