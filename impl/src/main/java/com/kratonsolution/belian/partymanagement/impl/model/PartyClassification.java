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

import com.kratonsolution.belian.partymanagement.api.model.PartyClassificationType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
@Entity
@Table(name="party_classification")
public class PartyClassification
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column
	@Enumerated(EnumType.STRING)
	private PartyClassificationType type;
	
	@Column
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="fk_party")
	private Party party;
	
	@Version
	private Long version;
}
