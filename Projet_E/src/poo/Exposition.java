package poo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XStreamAlias("Expo")
public class Exposition extends Evenement implements Promotable {
    
    @XStreamAsAttribute
    private String titre;
    
    @XStreamAsAttribute
    private boolean online;
    
    @XStreamAsAttribute
    private String date;

    @XStreamAsAttribute
    private double price;

    private String description;
    
    @XStreamImplicit(itemFieldName = "artist")
    private ArrayList<Artist> artists;

    public Exposition(String titre, String description, double price, boolean online, Date dateEvent) {
        super(titre, dateEvent);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.titre = titre;
        this.description = description;
        this.price = price;
        this.online = online;
        this.date = sdf.format(dateEvent);
        this.artists = new ArrayList<>();
        if (price < 0) {
            throw new NegativePriceException("Le prix ne peut pas être négatif");
        }
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    @Override
    public double computePromo(double rate) {
        if (rate < 0 || rate > 100) {
            throw new IllegalArgumentException("Le taux doit être entre 0 et 100");
        }
        return this.price * (1 - rate / 100);
    }

    public List<Artist> searchArtistsByCountry(String country) {
        return artists.stream()
                      .filter(artist -> artist.getCountry().equalsIgnoreCase(country))
                      .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(getDateEvent());

        StringBuilder sb = new StringBuilder();
        sb.append("**************************************\n");
        sb.append("* Exposition : ").append(getTexte()).append("\n");
        sb.append("* Date : ").append(formattedDate).append("\n");
        sb.append("* Description : ").append(description).append("\n");

        sb.append("* Artistes : ");
        int artistCount = artists.size();
        for (int i = 0; i < Math.min(artistCount, 2); i++) {
            sb.append(artists.get(i).getFirstname()).append(" ").append(artists.get(i).getLastname());
            if (i < artistCount - 1 && i < 1) {
                sb.append(", ");
            } else if (i == 1 && artistCount > 2) {
                sb.append(", ");
            }
        }
        if (artistCount > 2) {
            sb.append("...");
        }
        sb.append("\n");

        sb.append("* Prix : ");
        if (price == 0) {
            sb.append("Gratuit");
        } else {
            sb.append(String.format("%.2f", price)).append(" €");
        }
        sb.append("\n");

        sb.append("* Mode : ").append(online ? "en ligne" : "en présentiel").append("\n");
        sb.append("**************************************");

        return sb.toString();
    }
}
