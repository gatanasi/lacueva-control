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
 * Entity implementation class for Entity: Input
 *
 */
@Entity
@Table(name = "CUEVA_INPUTS")
public class Input {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_INPUTS_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_INPUTS_ID", sequenceName = "CUEVA_SEQ_INPUTS_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "INPUT_ID")
	private Long id;

	@Column(name = "INPUT_DATE", nullable = false)
	private Date inputDate;

	@OneToOne
	@JoinColumn(name = "INPUT_ITEM_ID")
	private Item inputItem;

	@Column(name = "INPUT_QUANTITY", nullable = false)
	private Integer inputQuantity;

	@OneToOne
	@JoinColumn(name = "INPUT_PROVIDER_ID")
	private Provider inputProvider;

	@OneToOne
	@JoinColumn(name = "INPUT_SHOP_ID")
	private Shop inputShop;

	public Input() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Item getInputItem() {
		return inputItem;
	}

	public void setInputItem(Item inputItem) {
		this.inputItem = inputItem;
	}

	public Integer getInputQuantity() {
		return inputQuantity;
	}

	public void setInputQuantity(Integer inputQuantity) {
		this.inputQuantity = inputQuantity;
	}

	public Provider getInputProvider() {
		return inputProvider;
	}

	public void setInputProvider(Provider inputProvider) {
		this.inputProvider = inputProvider;
	}

	public Shop getInputShop() {
		return inputShop;
	}

	public void setInputShop(Shop inputShop) {
		this.inputShop = inputShop;
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
