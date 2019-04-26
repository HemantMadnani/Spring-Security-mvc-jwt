package co.in.springsecwithhib.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "requestDate")
	private Date requestDate;

	@Column(name = "requestType")
	private String requestType;

	@Column(name = "requestData")
	private String requestData;

	@Column(name = "responseData")
	private String responseData;

	@Column(name = "responseDate")
	private Date responseDate;

	@Column(name = "status")
	private String status;

	@Column(name = "country")
	private String country;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "organisationName")
	private String organisationName;

	@Column(name = "organisationId")
	private String organisationId;

	/**
	 * @return the id
	 */
	public int getId() {

		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {

		this.id = id;
	}

	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {

		return requestDate;
	}

	/**
	 * @param requestDate
	 *            the requestDate to set
	 */
	public void setRequestDate(final Date requestDate) {

		this.requestDate = requestDate;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {

		return requestType;
	}

	/**
	 * @param requestType
	 *            the requestType to set
	 */
	public void setRequestType(final String requestType) {

		this.requestType = requestType;
	}

	/**
	 * @return the requestData
	 */
	public String getRequestData() {

		return requestData;
	}

	/**
	 * @param requestData
	 *            the requestData to set
	 */
	public void setRequestData(final String requestData) {

		this.requestData = requestData;
	}

	/**
	 * @return the responseData
	 */
	public String getResponseData() {

		return responseData;
	}

	/**
	 * @param responseData
	 *            the responseData to set
	 */
	public void setResponseData(final String responseData) {

		this.responseData = responseData;
	}

	/**
	 * @return the responseDate
	 */
	public Date getResponseDate() {

		return responseDate;
	}

	/**
	 * @param responseDate
	 *            the responseDate to set
	 */
	public void setResponseDate(final Date responseDate) {

		this.responseDate = responseDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {

		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {

		this.status = status;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {

		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {

		this.country = country;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {

		return zipcode;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(final String zipcode) {

		this.zipcode = zipcode;
	}

	/**
	 * @return the organisationName
	 */
	public String getOrganisationName() {

		return organisationName;
	}

	/**
	 * @param organisationName
	 *            the organisationName to set
	 */
	public void setOrganisationName(final String organisationName) {

		this.organisationName = organisationName;
	}

	/**
	 * @return the organisationId
	 */
	public String getOrganisationId() {

		return organisationId;
	}

	/**
	 * @param organisationId
	 *            the organisationId to set
	 */
	public void setOrganisationId(final String organisationId) {

		this.organisationId = organisationId;
	}

}
