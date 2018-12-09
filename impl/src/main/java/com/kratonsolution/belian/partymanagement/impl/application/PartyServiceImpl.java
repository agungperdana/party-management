package com.kratonsolution.belian.partymanagement.impl.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.kratonsolution.belian.partymanagement.api.application.PartyCreateCommand;
import com.kratonsolution.belian.partymanagement.api.application.PartyData;
import com.kratonsolution.belian.partymanagement.api.application.PartyDeleteCommand;
import com.kratonsolution.belian.partymanagement.api.application.PartyService;
import com.kratonsolution.belian.partymanagement.api.application.PartyUpdateCommand;
import com.kratonsolution.belian.partymanagement.impl.model.Party;
import com.kratonsolution.belian.partymanagement.impl.repository.PartyRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Service
public class PartyServiceImpl implements PartyService
{
	@Autowired
	private PartyRepository repo;
	
	@Autowired
	private PartyMapper mapper;
	
	@Override
	public Optional<PartyData> findById(@NonNull String id)
	{
		return Optional.ofNullable(mapper.toData(repo.findById(id).orElse(null)));
	}

	@Override
	public Optional<PartyData> findByCode(@NonNull String code)
	{
		return Optional.ofNullable(mapper.toData(repo.findOneByCode(code).orElse(null)));
	}

	@Override
	public List<PartyData> findAll()
	{
		return mapper.toDatas(repo.findAll());
	}

	@Override
	public List<PartyData> findAll(@NonNull Integer page, @NonNull Integer rowPerPage)
	{
		return mapper.toDatas(repo.findAll(PageRequest.of(page, rowPerPage)).getContent());
	}

	@Override
	public List<PartyData> findAllByName(@NonNull String name)
	{
		return mapper.toDatas(repo.findAllByNameLike(name+"%"));
	}

	@Override
	public List<PartyData> findAllByName(@NonNull String name, @NonNull Integer page, Integer rowPerPage)
	{
		return mapper.toDatas(repo.findAllByNameLike(name+"%", PageRequest.of(page, rowPerPage)));
	}

	@Override
	public PartyData create(@NonNull PartyCreateCommand command)
	{
		Party party = new Party(command.getCode(), command.getName(), command.getPartyType(), 
				command.getBirthDate(), command.getTaxCode());
		
		repo.save(party);
		log.info("Creating new Party {}", party);
		
		return mapper.toData(party);
	}

	@Override
	public PartyData update(@NonNull PartyUpdateCommand command)
	{
		Optional<Party> opt = repo.findOneByCode(command.getCode());
		Preconditions.checkState(opt.isPresent(), "Party with code [%s] does not exist", command.getCode());
		opt.get().update(command.getName(), command.getPartyType(), command.getBirthDate(), command.getTaxCode());
		return mapper.toData(opt.get());
	}

	@Override
	public PartyData delete(@NonNull PartyDeleteCommand command)
	{
		Optional<Party> opt = repo.findOneByCode(command.getCode());
		Preconditions.checkState(opt.isPresent(), "Party with code [%s] does not exist", command.getCode());
		repo.delete(opt.get());
		return mapper.toData(opt.get());
	}

}
