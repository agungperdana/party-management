package com.kratonsolution.belian.partymanagement.impl.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipCreateCommand;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipData;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipDeleteCommand;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipService;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipUpdateCommand;
import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;
import com.kratonsolution.belian.partymanagement.impl.repository.PartyRelationshipRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Service
public class PartyRelationshipServiceImpl implements PartyRelationshipService
{
	@Autowired
	private PartyRelationshipRepository repo;

	@Autowired
	private PartyRelationshipMapper mapper;

	@Override
	public Optional<PartyRelationshipData> findById(@NonNull String id)
	{
		return Optional.ofNullable(mapper.toData(repo.findById(id).orElse(null)));
	}


	@Override
	public List<PartyRelationshipData> findAll()
	{
		return mapper.toDatas(repo.findAll());
	}

	@Override
	public List<PartyRelationshipData> findAll(@NonNull Integer page, @NonNull Integer rowPerPage)
	{
		return mapper.toDatas(repo.findAll(PageRequest.of(page, rowPerPage)).getContent());
	}

	@Override
	public List<PartyRelationshipData> findAll(@NonNull LocalDate start, @NonNull String fromPartyCode, @NonNull String toPartyCode, @NonNull RelationshipType type)
	{
		return null;
	}

	@Override
	public PartyRelationshipData create(@NonNull PartyRelationshipCreateCommand command)
	{
		return null;
	}

	@Override
	public PartyRelationshipData update(@NonNull PartyRelationshipUpdateCommand command)
	{
		return null;
	}

	@Override
	public PartyRelationshipData delete(@NonNull PartyRelationshipDeleteCommand command)
	{
		return null;
	}
}
