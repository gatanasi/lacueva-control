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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Temporal(TemporalType.DATE)
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
		StringBuilder sb = new StringBuilder("Withdrawal [");
		sb.append("withdrawalId=").append(getId()).append(", withdrawalDate=")
				.append(getWithdrawalDate()).append(", withdrawalShopName=");
		if (getWithdrawalShop() != null) {
			sb.append(getWithdrawalShop().getShopName());
		}
		sb.append(", withdrawalAmount=").append(getWithdrawalAmount())
				.append("]");

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Withdrawal) && getId() != null) {
			final Withdrawal that = (Withdrawal) obj;
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
