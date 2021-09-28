package annotations;

public class Address {

	private String name;

	private String lastname;

	private String street;

	private String location;

	@GetProperty("Nachname")
	public String getLastname() {
		return lastname;
	}

	@SetProperty("Nachname")
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@GetProperty("PLZ, Ort")
	public String getLocation() {
		return location;
	}

	@SetProperty("PLZ, Ort")
	public void setLocation(String location) {
		this.location = location;
	}

	@GetProperty("Vorname")
	public String getName() {
		return name;
	}

	@SetProperty("Vorname")
	public void setName(String name) {
		this.name = name;
	}

	@GetProperty("Strasse, Nr")
	public String getStreet() {
		return street;
	}

	@SetProperty("Strasse, Nr")
	public void setStreet(String street) {
		this.street = street;
	}

}
