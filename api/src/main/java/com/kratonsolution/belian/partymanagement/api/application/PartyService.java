package com.kratonsolution.belian.partymanagement.api.application;

import java.util.List;
import java.util.Optional;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface PartyService
{
	public Optional<PartyData> findById(@NonNull String id);
	
	public Optional<PartyData> findByCode(@NonNull String code);
	
	public List<PartyData> findAll();
	
	public List<PartyData> findAll(@NonNull Integer page, @NonNull Integer rowPerPage);
	
	public List<PartyData> findAllByName(@NonNull String name);
	
	public List<PartyData> findAllByName(@NonNull String name, @NonNull Integer page, @NonNull Integer rowPerPage);
	
	public PartyData create(@NonNull PartyCreateCommand command);
	
	public PartyData update(@NonNull PartyUpdateCommand command);
	
	public PartyData delete(@NonNull PartyDeleteCommand command);
}
