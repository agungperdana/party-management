package com.kratonsolution.belian.partymanagement.api.application;

import java.time.LocalDate;

import com.kratonsolution.belian.partymanagement.api.model.PartyType;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PartyUpdateCommand
{
	@NonNull
	private String code;

	@NonNull
	private String name;
	
	@NonNull
	private PartyType partyType;

	@NonNull
	private LocalDate birthDate;

	private String taxCode;
}
