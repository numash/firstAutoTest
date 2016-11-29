/**
 * Created by numash on 25.11.2016.
 */
//poker player entity
public class PokerPlayer {

    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String city;
    private String country;
    private String address;
    private String phone;

    //constructor
    public PokerPlayer(
            String username,
            String email,
            String firstname,
            String lastname,
            String city,
            String country,
            String address,
            String phone) {

        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
    }

    //fills poker player fields with random data
    public static PokerPlayer CreateRandomPockerPlayer() {

        RandomManager randomManager = new RandomManager();
        String randomString = randomManager.getRandomString(5);

        return new PokerPlayer(
                "user68_" + randomString,
                "user68_" + randomString + "@gmail.com",
                "first",
                "last",
                "City.",
                "UKRAINE",
                "Address68, " + randomString,
                "+312345678, 890");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof PokerPlayer)) return false;

        PokerPlayer that = (PokerPlayer) o;

        if (!username.equals(that.username)) return false;
        if (!email.equals(that.email)) return false;
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
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }
}
