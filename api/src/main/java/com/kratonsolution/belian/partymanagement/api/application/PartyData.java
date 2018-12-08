package com.kratonsolution.belian.partymanagement.api.application;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.kratonsolution.belian.partymanagement.api.model.PartyType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PartyData
{
	private String code;

	private String name;
	
	private PartyType partyType;

	private LocalDate birthDate;

	private String taxCode;
	
	private Set<PartyClassificationData> classifications = new HashSet<>();
	
	private Set<ContactData> contacts = new HashSet<>();
	
	private Set<AddressData> addresses = new HashSet<>();
	
	private Set<PartyRoleData> roles = new HashSet<>();
	
	private Set<PartyRelationshipData> relationships = new HashSet<>();
}
