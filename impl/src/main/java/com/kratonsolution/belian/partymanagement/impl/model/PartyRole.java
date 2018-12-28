package com.kratonsolution.belian.partymanagement.impl.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.kratonsolution.belian.partymanagement.api.model.RoleType;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="party_role")
public class PartyRole
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column
	private LocalDate start;

	@Setter
	@Column
	private LocalDate end;

	@Column
	private RoleType type;
	
	@ManyToOne
	@JoinColumn(name="fk_party")
	private Party party;
	
	@Version
	private Long version;
	
	PartyRole(){}
	
	public PartyRole(@NonNull Party parent, @NonNull LocalDate start, LocalDate end, @NonNull RoleType type) {
		
		this.start = start;
		this.end = end;
		this.type = type;
		this.party = parent;
	}
}
