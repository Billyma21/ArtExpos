package poo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("artist")
public class Artist {

    private String firstname;
    private String lastname;
    private String country;

    public Artist(String firstname, String lastname, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCountry() {
        return country;
    }
}
