package com.kratonsolution.belian.partymanagement.impl.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.google.common.base.Preconditions;
import com.kratonsolution.belian.partymanagement.api.model.AddressType;
import com.kratonsolution.belian.partymanagement.api.model.PartyType;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Entity
@Table(name="party")
public class Party
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Getter
	@Column
	private String code;

	@Getter
	@Column
	private String name;

	@Getter
	@Column
	private PartyType partyType;

	@Getter
	@Column
	private LocalDate birthDate;

	@Getter
	@Column
	private String taxCode;

	@Version
	private Long version;
	
	@OneToMany(mappedBy="party", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PartyClassification> classifications = new HashSet<>();

	@OneToMany(mappedBy="party", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Contact> contacts = new HashSet<>();

	@OneToMany(mappedBy="party", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Address> addresses = new HashSet<>();

	@OneToMany(mappedBy="party", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PartyRole> roles = new HashSet<>();

	@OneToMany(mappedBy="party", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<PartyRelationship> relationships = new HashSet<>();

	Party(){}

	public Party(@NonNull String code, @NonNull String name, @NonNull PartyType partyType, @NonNull LocalDate birthDate, String taxCode) {

		this.code = code;
		this.name = name;
		this.partyType = partyType;
		this.birthDate = birthDate;
		this.taxCode = taxCode;
	}
	
	public void update(String name, PartyType partyType, LocalDate birthDate, String taxCode) {
		
		if(name != null && !this.name.equals(name)) {
			this.name = name;
		}
		
		if(partyType != null && !this.partyType.equals(partyType)) {
			this.partyType = partyType;
		}
		
		if(birthDate != null && !this.birthDate.equals(birthDate)) {
			this.birthDate = birthDate;
		}
		
		if(taxCode != null && !this.taxCode.equals(taxCode)) {
			this.taxCode = taxCode;
		}
	}
	
	public Address createAddress(@NonNull AddressType type, @NonNull String content) {
		
		Optional<Address> duplicate = this.addresses.stream().filter(add -> {
			return add.getType().equals(type) && add.getContent().equalsIgnoreCase(content);
		}).findFirst();
		
		Preconditions.checkState(!duplicate.isPresent(), "Address with same description already exist.");
		
		Address address = new Address(this, type, content);
		address.activate();
		
		this.addresses.add(address);
		
		return address;
	}
}
