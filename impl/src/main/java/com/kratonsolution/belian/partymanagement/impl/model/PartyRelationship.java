package com.kratonsolution.belian.partymanagement.impl.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="party_relationship")
public class PartyRelationship
{
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column
	private LocalDate start;

	@Column
	private LocalDate end;

	@Column
	private String fromPartyCode;

	@Column
	private String toPartyCode;

	@Column
	@Enumerated(EnumType.STRING)
	private RelationshipType type;
	
	@Version
	private Long version;
}
