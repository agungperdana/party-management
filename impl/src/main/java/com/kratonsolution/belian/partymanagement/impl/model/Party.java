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
import com.kratonsolution.belian.partymanagement.api.model.ContactType;
import com.kratonsolution.belian.partymanagement.api.model.PartyType;
import com.kratonsolution.belian.partymanagement.api.model.RoleType;

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
	
	public Address addAddress(@NonNull AddressType type, @NonNull String content) {
		
		Optional<Address> duplicate = this.addresses.stream().filter(add -> {
			return add.getType().equals(type) && add.getContent().equalsIgnoreCase(content);
		}).findFirst();
		
		Preconditions.checkState(!duplicate.isPresent(), "Address with same description already exist.");
		
		Address address = new Address(this, type, content);
		address.activate();
		
		this.addresses.add(address);
		
		return address;
	}
	
	public void deleteAddress(@NonNull Address address) {
		
		this.addresses.removeIf(add -> add.getId().equals(address.getId()));
	}
	
	public Contact addContact(@NonNull ContactType type, @NonNull String content, String note) {
		
		Optional<Contact> duplicate = this.contacts.stream().filter(con -> {
			return con.getContent().equals(content) && con.getType().equals(type);
		}).findFirst();
		
		Preconditions.checkState(!duplicate.isPresent(), "Contact with same description already exist.");
		
		Contact contact = new Contact(this, type, content, note);
		contact.activate();
		
		this.contacts.add(contact);
		
		return contact;
	}
	
	public void deleteContact(@NonNull Contact contact) {
		this.contacts.removeIf(con -> con.getId().equals(contact.getId()));
	}
	
	public PartyRole addRole(@NonNull LocalDate start, LocalDate end, @NonNull RoleType type) {
		
		Optional<PartyRole> duplicate = this.roles.stream().filter(role -> {
			return role.getType().equals(type) && role.getStart().equals(start);
		}).findFirst();
		
		Preconditions.checkState(!duplicate.isPresent(), "Role with same type already exist.");
		
		PartyRole role = new PartyRole(this, start, end, type);
		this.roles.add(role);
		
		return role;
	}
	
	public void deleteRole(@NonNull PartyRole role) {
		this.roles.removeIf(com->com.getId().equals(role.getId()));
	}
	
}
