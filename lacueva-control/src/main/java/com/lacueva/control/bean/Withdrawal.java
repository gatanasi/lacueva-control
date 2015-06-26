package com.lacueva.control.bean;

import java.io.Serializable;
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
 * Entity implementation class for Entity: Withdrawal
 *
 */
@Entity
@Table(name = "CUEVA_WITHDRAWALS")
public class Withdrawal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7513645680606430327L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUEVA_SEQ_WITHDRAWALS_ID")
	@SequenceGenerator(name = "CUEVA_SEQ_WITHDRAWALS_ID", sequenceName = "CUEVA_SEQ_WITHDRAWALS_ID", initialValue = 1, allocationSize = 1)
	@Column(name = "WITHDRAWAL_ID")
	private Long id;

	@Column(name = "WITHDRAWAL_DATE", nullable = false)
	private Date withdrawalDate;

	@OneToOne
	@JoinColumn(name = "WITHDRAWAL_SHOP_ID")
	private Shop withdrawalShop;

	@Column(name = "WITHDRAWAL_AMOUNT", nullable = false)
	private Float withdrawalAmount;

	public Withdrawal() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getWithdrawalDate() {
		return withdrawalDate;
	}

	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	public Shop getWithdrawalShop() {
		return withdrawalShop;
	}

	public void setWithdrawalShop(Shop withdrawalShop) {
		this.withdrawalShop = withdrawalShop;
	}

	public Float getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(Float withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	@Override
	public String toString()

	{
		return "Withdrawal [withdrawalId=" + getId() + ", withdrawalDate="
				+ getWithdrawalDate() + ", withdrawalShopName="
				+ getWithdrawalShop().getShopName() + ", withdrawalAmount="
				+ getWithdrawalAmount() + "]";
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
