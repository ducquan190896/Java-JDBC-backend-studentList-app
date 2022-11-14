package Model;

public class Student {
    private int id;
    private String firstname;
    private String lastname;
    private String postcode;
    private String streetaddress;
    private String city;
    public Student(int id, String firstname, String lastname, String postcode, String streetaddress, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.postcode = postcode;
        this.streetaddress = streetaddress;
        this.city = city;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getStreetaddress() {
        return streetaddress;
    }
    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return id + ": " + firstname + " " + lastname + ", " + streetaddress + ", " + postcode
                +  " " + city.toUpperCase();
    }

    
}
