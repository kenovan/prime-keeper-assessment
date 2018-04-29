package com.prime.keeper.assessment.model.account;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "app_user_account")
@JsonInclude(value = Include.NON_NULL)
public class AppUserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "balance_amount")
	private Integer balanceAmount;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<AppUserAccountDetail> appUserAccountDetails;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Integer balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<AppUserAccountDetail> getAppUserAccountDetails() {
		return appUserAccountDetails;
	}

	public void setAppUserAccountDetails(List<AppUserAccountDetail> appUserAccountDetails) {
		this.appUserAccountDetails = appUserAccountDetails;
	}
	
	
}
