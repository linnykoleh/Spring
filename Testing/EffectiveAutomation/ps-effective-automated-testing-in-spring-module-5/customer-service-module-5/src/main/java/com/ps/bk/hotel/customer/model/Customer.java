package com.ps.bk.hotel.customer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Customers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	@Id
	@GeneratedValue
	private long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "suffix")
	private String suffix;
	@Column(name = "date_of_last_stay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date dateOfLastStay;

	public Customer() {
	}

	@JsonCreator
	public Customer(@JsonProperty(value = "id", defaultValue = "0L") long id,
			@JsonProperty(value = "firstName", required = true) String firstName,
			@JsonProperty(value = "lastName", required = true) String lastName,
			@JsonProperty("middleName") String middleName, @JsonProperty("suffix") String suffix,
			@JsonProperty("lastStayDate") Date dateOfLastStay) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.dateOfLastStay = dateOfLastStay;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@JsonProperty("lastStayDate")
	public Date getDateOfLastStay() {
		return dateOfLastStay;
	}

	public void setDateOfLastStay(Date dateOfLastStay) {
		this.dateOfLastStay = dateOfLastStay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfLastStay == null) ? 0 : dateOfLastStay.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (dateOfLastStay == null) {
			if (other.dateOfLastStay != null)
				return false;
		} else if (!dateOfLastStay.equals(other.dateOfLastStay))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		return true;
	}

	public static class CustomerBuilder {
		private long id;
		private String firstName;
		private String lastName;
		private String middleName;
		private String suffix;
		private Date dateOfLastStay;

		public CustomerBuilder id(long id) {
			this.id = id;
			return this;
		}

		public CustomerBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public CustomerBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public CustomerBuilder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}

		public CustomerBuilder suffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		public CustomerBuilder dateOfLastStay(Date dateOfLastStay) {
			this.dateOfLastStay = dateOfLastStay;
			return this;
		}

		public Customer build() {
			return new Customer(id, firstName, lastName, middleName, suffix, dateOfLastStay);
		}
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", suffix=" + suffix + ", dateOfLastStay=" + dateOfLastStay + "]";
	}

}
