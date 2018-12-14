package com.kratonsolution.belian.partymanagement.impl.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kratonsolution.belian.partymanagement.api.model.RelationshipType;
import com.kratonsolution.belian.partymanagement.impl.model.PartyRelationship;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface PartyRelationshipRepository extends JpaRepository<PartyRelationship, String>
{
	@Query("FROM PartyRelationship PR WHERE PR.start =:start AND "
			+ "PR.fromPartyCode =:fromPartyCode AND "
			+ "PR.toPartyCode =:toPartyCode AND "
			+ "PR.type =:type ")
	public List<PartyRelationship> findAll(@NonNull @Param("start") LocalDate start, 
				@NonNull @Param("fromPartyCode")String fromPartyCode, 
				@NonNull @Param("toPartyCode")String toPartyCode, 
				@NonNull @Param("type")RelationshipType type);
	
}
