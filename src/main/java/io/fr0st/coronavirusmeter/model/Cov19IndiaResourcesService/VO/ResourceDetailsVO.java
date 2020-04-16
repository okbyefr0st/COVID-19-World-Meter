package io.fr0st.coronavirusmeter.model.Cov19IndiaResourcesService.VO;

public class ResourceDetailsVO {

	private String category;
	private String city;
	private String contact;
	private String descriptionandorserviceprovided;
	private String nameoftheorganisation;
	private String phonenumber;
	private String state;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescriptionandorserviceprovided() {
		return descriptionandorserviceprovided;
	}

	public void setDescriptionandorserviceprovided(String descriptionandorserviceprovided) {
		this.descriptionandorserviceprovided = descriptionandorserviceprovided;
	}

	public String getNameoftheorganisation() {
		return nameoftheorganisation;
	}

	public void setNameoftheorganisation(String nameoftheorganisation) {
		this.nameoftheorganisation = nameoftheorganisation;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ResourceDetailsVO [category=" + category + ", city=" + city + ", contact=" + contact
				+ ", descriptionandorserviceprovided=" + descriptionandorserviceprovided + ", nameoftheorganisation="
				+ nameoftheorganisation + ", phonenumber=" + phonenumber + ", state=" + state + "]";
	}
}
