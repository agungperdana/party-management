package com.kratonsolution.belian.partymanagement.api.application;

import java.time.LocalDate;

import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;

import lombok.Getter;

import lombok.Setter;import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PartyRelationshipDeleteCommand
{
	@NonNull
	private LocalDate start;

	private LocalDate end;

	@NonNull
	private String fromPartyCode;

	@NonNull
	private String toPartyCode;

	@NonNull
	private RelationshipType type;
}
