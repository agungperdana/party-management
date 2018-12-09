package com.kratonsolution.belian.partymanagement.impl.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.kratonsolution.belian.partymanagement.api.model.AddressType;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="address")
public class Address
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column
	private String content;

	@Column
	@Enumerated(EnumType.STRING)
	private AddressType type;

	@Column
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="fk_party")
	private Party party;
	
	@Version
	private Long version;
	
	Address(){}
	
	public Address(@NonNull Party parent, @NonNull AddressType type, @NonNull String content){
		
		this.content = content;
		this.type = type;
		this.party = parent;
	}
	
	public void activate() {
		
		if(!this.active) {
			this.active = true;
		}
	}
	
	public void deactivate() {
		
		if(this.active) {
			this.active = false;
		}
	}
}
