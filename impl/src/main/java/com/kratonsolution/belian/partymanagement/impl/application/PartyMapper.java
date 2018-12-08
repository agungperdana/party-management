package com.kratonsolution.belian.partymanagement.impl.application;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import com.kratonsolution.belian.partymanagement.api.application.PartyData;
import com.kratonsolution.belian.partymanagement.impl.model.Party;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Mapper(componentModel="spring")
public interface PartyMapper
{
	public PartyData toData(Party party);
	
	public List<PartyData> toDatas(Collection<Party> partys);
}
