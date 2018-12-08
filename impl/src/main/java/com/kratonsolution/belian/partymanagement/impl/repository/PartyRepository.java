package com.kratonsolution.belian.partymanagement.impl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.belian.partymanagement.impl.model.Party;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface PartyRepository extends JpaRepository<Party, String>
{
	public Optional<Party> findOneByCode(@NonNull String code);
	
	public List<Party> findAllByNameLike(@NonNull String name);
	
	public List<Party> findAllByNameLike(@NonNull String name, @NonNull Pageable pageable);
}
