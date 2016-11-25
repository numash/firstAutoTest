/**
 * Created by numash on 25.11.2016.
 */
public class PockerPlayer {
    private RandomManager randomManager = new RandomManager();

    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String city;
    private String country;
    private String address;
    private String phone;

    public PockerPlayer() {
        String randomString = randomManager.getRandomString(5);

        username = "user68_" + randomString;
        email = "user68_" + randomString + "@gmail.com";
        password = "pass_Word68";
        firstname = "first";
        lastname = "last";
        city = "City.";
        country = "UKRAINE";
        address = "Address68, " + randomString;
        phone = "+312345678, 890";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "PockerPlayer{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PockerPlayer)) return false;

        PockerPlayer that = (PockerPlayer) o;

        if (!username.equals(that.username)) return false;
        if (!email.equals(that.email)) return false;
        if (!password.equals(that.password)) return false;
        if (!firstname.equals(that.firstname)) return false;
        if (!lastname.equals(that.lastname)) return false;
        if (!city.equals(that.city)) return false;
        if (!country.equals(that.country)) return false;
        if (!address.equals(that.address)) return false;
        return phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }
}
