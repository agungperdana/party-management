package com.kratonsolution.belian.partymanagement.impl.application;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipData;
import com.kratonsolution.belian.partymanagement.impl.model.PartyRelationship;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Mapper(componentModel="spring")
public interface PartyRelationshipMapper
{
	public PartyRelationshipData toData(PartyRelationship relationship);
	
	public List<PartyRelationshipData> toDatas(Collection<PartyRelationship> relationships);
}
