package com.kratonsolution.belian.partymanagement.impl.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.kratonsolution.belian.partymanagement.api.model.ContactType;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="contact")
public class Contact
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column
	private String content;

	@Column
	@Enumerated(EnumType.STRING)
	private ContactType type;

	@Setter
	@Column
	private String note;

	@Column
	private boolean active;

	@ManyToOne
	@JoinColumn(name="fk_party")
	private Party parent;

	@Version
	private Long version;

	Contact(){}

	public Contact(@NonNull Party parent, @NonNull ContactType type, @NonNull String content, String note)
	{
		this.parent = parent;
		this.content = content;
		this.type = type;
		this.note = note;
	}

	public void activate() {

		if(!this.active) {
			this.active = true;
		}
	}

	public void deactivate() {

		if(this.active) {
			this.active = false;
		}
	}
}
