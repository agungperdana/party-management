package com.kratonsolution.belian.partymanagement.api.application;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PartyDeleteCommand
{
	@NonNull
	private String code;
}
