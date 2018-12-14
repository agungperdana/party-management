package com.kratonsolution.belian.partymanagement.impl.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipCreateCommand;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipData;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipDeleteCommand;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipService;
import com.kratonsolution.belian.partymanagement.api.application.PartyRelationshipUpdateCommand;
import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;
import com.kratonsolution.belian.partymanagement.impl.model.PartyRelationship;
import com.kratonsolution.belian.partymanagement.impl.repository.PartyRelationshipRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
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
		return mapper.toDatas(repo.findAll(start, fromPartyCode, toPartyCode, type));
	}

	@Override
	public PartyRelationshipData create(@NonNull PartyRelationshipCreateCommand command)
	{

		List<PartyRelationship> list = repo.findAll(command.getStart(), command.getFromPartyCode(), command.getToPartyCode(), command.getType());
		Optional<PartyRelationship> duplicate = list.stream().filter(pr -> {
			return pr.getStart().equals(command.getStart()) 
					&& pr.getEnd() == null 
					&& pr.getFromPartyCode().equals(command.getFromPartyCode()) 
					&& pr.getToPartyCode().equals(command.getToPartyCode()) 
					&& pr.getType().equals(command.getType());
		}).findFirst();

		Preconditions.checkState(!duplicate.isPresent(),"Party Relationship with active state already exist");

		PartyRelationship relationship = new PartyRelationship(command.getStart(), command.getEnd(), command.getFromPartyCode(), command.getToPartyCode(), command.getType());
		repo.save(relationship);

		log.info("Creating new PartyRelationship {}", relationship);

		return mapper.toData(relationship);
	}

	@Override
	public PartyRelationshipData update(@NonNull PartyRelationshipUpdateCommand command)
	{
		List<PartyRelationship> list = repo.findAll(command.getStart(), command.getFromPartyCode(), command.getToPartyCode(), command.getType());

		Optional<PartyRelationship> target = list.stream().filter(pr -> {
			return pr.getStart().equals(command.getStart()) 
					&& pr.getFromPartyCode().equals(command.getFromPartyCode()) 
					&& pr.getToPartyCode().equals(command.getToPartyCode()) 
					&& pr.getType().equals(command.getType());
		}).findFirst();

		Preconditions.checkState(target.isPresent(),"Target PartyRelationship data does not exist");

		target.get().setEnd(command.getEnd());
		repo.delete(target.get());

		log.info("Updating PartyRelationship data {}", target.get());

		return mapper.toData(target.get());
	}

	@Override
	public PartyRelationshipData delete(@NonNull PartyRelationshipDeleteCommand command)
	{
		List<PartyRelationship> list = repo.findAll(command.getStart(), command.getFromPartyCode(), command.getToPartyCode(), command.getType());

		if(command.getEnd() == null) {
			Optional<PartyRelationship> target = list.stream().filter(pr -> {
				return pr.getStart().equals(command.getStart()) 
						&& pr.getEnd() == null
						&& pr.getFromPartyCode().equals(command.getFromPartyCode()) 
						&& pr.getToPartyCode().equals(command.getToPartyCode()) 
						&& pr.getType().equals(command.getType());
			}).findFirst();
			
			Preconditions.checkState(target.isPresent(),"Target PartyRelationship data does not exist");
			
			repo.delete(target.get());
			log.info("Delete PartyRelationship data {}", target.get());
			
			return mapper.toData(target.get());
		}
		else {
			
			Optional<PartyRelationship> target = list.stream().filter(pr -> {
				return pr.getStart().equals(command.getStart()) 
						&& pr.getEnd().equals(command.getEnd())
						&& pr.getFromPartyCode().equals(command.getFromPartyCode()) 
						&& pr.getToPartyCode().equals(command.getToPartyCode()) 
						&& pr.getType().equals(command.getType());
			}).findFirst();
			
			Preconditions.checkState(target.isPresent(),"Target PartyRelationship data does not exist");
			
			repo.delete(target.get());
			log.info("Delete PartyRelationship data {}", target.get());
			
			return mapper.toData(target.get());
		}
	}
}
