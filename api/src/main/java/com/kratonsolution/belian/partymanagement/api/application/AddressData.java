package com.kratonsolution.belian.partymanagement.api.application;

import com.kratonsolution.belian.partymanagement.api.model.AddressType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class AddressData
{
	private String content;
	
	private AddressType type;
	
	private String note;
}
