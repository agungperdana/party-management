package com.kratonsolution.belian.partymanagement.impl.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Id;

import com.kratonsolution.belian.partymanagement.api.model.PartyType;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
public class Party
{
	@Id
	private String id = UUID.randomUUID().toString();

	private String code;

	private String name;

	private PartyType partyType;

	private LocalDate birthDate;

	private String taxCode;

	private Set<PartyClassification> classifications = new HashSet<>();

	private Set<Contact> contacts = new HashSet<>();

	private Set<Address> addresses = new HashSet<>();

	private Set<PartyRole> roles = new HashSet<>();

	private Set<PartyRelationship> relationships = new HashSet<>();

	Party(){}

	public Party(@NonNull String code, @NonNull String name, @NonNull PartyType partyType, @NonNull LocalDate birthDate, String taxCode) {

		this.code = code;
		this.name = name;
		this.partyType = partyType;
		this.birthDate = birthDate;
		this.taxCode = taxCode;
	}
}
