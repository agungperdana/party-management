package com.kratonsolution.belian.partymanagement.api.application;

import java.time.LocalDate;

import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PartyRelationshipUpdateCommand
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
