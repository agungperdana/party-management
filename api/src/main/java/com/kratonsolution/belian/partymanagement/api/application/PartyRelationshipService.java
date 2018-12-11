package com.kratonsolution.belian.partymanagement.api.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface PartyRelationshipService
{
	public Optional<PartyRelationshipData> findById(@NonNull String id);
	
	public List<PartyRelationshipData> findAll();
	
	public List<PartyRelationshipData> findAll(@NonNull Integer page, @NonNull Integer rowPerPage);

	public List<PartyRelationshipData> findAll(@NonNull LocalDate start, @NonNull String fromPartyCode, @NonNull String toPartyCode, @NonNull RelationshipType type);
	
	public PartyRelationshipData create(@NonNull PartyRelationshipCreateCommand command);
	
	public PartyRelationshipData update(@NonNull PartyRelationshipUpdateCommand command);
	
	public PartyRelationshipData delete(@NonNull PartyRelationshipDeleteCommand command);
	
}
