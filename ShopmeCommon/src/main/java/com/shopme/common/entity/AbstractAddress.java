package com.shopme.common.entity;





import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAddress extends IdBasedEntity {
	
	@Column(name = "first_name", nullable = false, length = 45)
	protected String firstName;
	
	@Column(name = "last_name", nullable = false, length = 45)
	protected String lastName;
	
	@Column(name = "u_name", nullable = false, length = 45)
	protected String cName;
	
	@Column(name = "Date_of_birth", nullable = false, length = 45)
	protected String dob;
	
	@Column(name = "Gender", nullable = false, length = 45)
	protected String gender;
	
	@Column(name = "aadhaar", nullable = false, length = 45)
	protected String aadhaar;
	
	@Column(name = "UDID", nullable = false, length = 45)
	protected String udid;
	
	@Column(name = "Guardian_Name", nullable = false, length = 45)
	protected String guardianName;
	
	@Column(name = "phone_number", nullable = false, length = 15)
	protected String phoneNumber;
	
	@Column(name = "guardian_Aadhaar", nullable = false, length = 45)
	protected String guardianAadhaar;
	
	@Column(name = "guardian_Relationship", nullable = false, length = 45)
	protected String guardianRelationship;
	
	
	
	
	@Column(name = "address_line_1", nullable = false, length = 64)
	protected String addressLine1;
	
	@Column(name = "address_line_2", length = 64)
	protected String addressLine2;
	
	@Column(nullable = false, length = 45)
	protected String city;
	
	@Column(nullable = false, length = 45)
	protected String state;
	
	@Column(name = "postal_code", nullable = false, length = 10)
	protected String postalCode;
	
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
	
	

	
	public String getDob() {
		return dob;
	}

	public void setDob(String string) {
		this.dob = string;
	}
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	
	

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	
	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	
	

	public String getGuardianAadhaar() {
		return guardianAadhaar;
	}

	public void setGuardianAadhaar(String guardianAadhaar) {
		this.guardianAadhaar = guardianAadhaar;
	}
	
	

	public String getGuardianRelationship() {
		return guardianRelationship;
	}

	public void setGuardianRelationship(String guardianRelationship) {
		this.guardianRelationship = guardianRelationship;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
	
}
