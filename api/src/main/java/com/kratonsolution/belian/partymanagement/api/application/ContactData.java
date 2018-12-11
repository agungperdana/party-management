package com.kratonsolution.belian.partymanagement.api.application;

import com.kratonsolution.belian.partymanagement.api.model.ContactType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class ContactData
{
	private String content;
	
	private ContactType type;
	
	private String note;
	
	private boolean active;
}
