package com.lacueva.control.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: BlankDisc
 *
 */
@Entity
@Table(name = "CUEVA_BLANK_DISCS")
public class BlankDisc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_BLANK_DISCS_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_BLANK_DISCS_ID", sequenceName = "CUEVA_SEQ_BLANK_DISCS_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "BLANK_ID")
	private Long id;

	@Column(name = "BLANK_DATE", nullable = false)
	private Date blankDiscDate;

	@OneToOne
	@JoinColumn(name = "BLANK_ITEM_ID")
	private Item blankDiscItem;

	@Column(name = "BLANK_QUANTITY", nullable = false)
	private Integer blankDiscQuantity;

	@OneToOne
	@JoinColumn(name = "BLANK_PROVIDER_ID")
	private Provider blankDiscProvider;

	public BlankDisc() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBlankDiscDate() {
		return blankDiscDate;
	}

	public void setBlankDiscDate(Date blankDiscDate) {
		this.blankDiscDate = blankDiscDate;
	}

	public Item getBlankDiscItem() {
		return blankDiscItem;
	}

	public void setBlankDiscItem(Item blankDiscItem) {
		this.blankDiscItem = blankDiscItem;
	}

	public Integer getBlankDiscQuantity() {
		return blankDiscQuantity;
	}

	public void setBlankDiscQuantity(Integer blankDiscQuantity) {
		this.blankDiscQuantity = blankDiscQuantity;
	}

	public Provider getBlankDiscProvider() {
		return blankDiscProvider;
	}

	public void setBlankDiscProvider(Provider blankDiscProvider) {
		this.blankDiscProvider = blankDiscProvider;
	}

	@Override
	public String toString()

	{
		return "BlankDisc [blankDiscId=" + getId() + ", blankDiscDate="
				+ getBlankDiscDate() + ", blankDiscItemType="
				+ getBlankDiscItem().getItemType().getText()
				+ ", blankDiscQuantity=" + getBlankDiscQuantity()
				+ ", blankDiscProviderName="
				+ getBlankDiscProvider().getProviderName() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Item)) {
			final Item that = (Item) obj;
			return getId().equals(that.getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().intValue();
		} else {
			return 0;
		}
	}
}
