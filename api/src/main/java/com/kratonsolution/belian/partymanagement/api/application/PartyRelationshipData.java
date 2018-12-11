package com.kratonsolution.belian.partymanagement.api.application;

import java.time.LocalDate;

import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PartyRelationshipData
{
	private LocalDate start;
	
	private LocalDate end;
	
	private String fromPartyCode;
	
	private String toPartyCode;
	
	private RelationshipType type;
}
