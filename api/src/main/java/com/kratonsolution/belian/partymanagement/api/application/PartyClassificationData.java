package com.kratonsolution.belian.partymanagement.api.application;

import com.kratonsolution.belian.partymanagement.api.model.PartyClassificationType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PartyClassificationData
{
	private PartyClassificationType type;
	
	private boolean active;
}
