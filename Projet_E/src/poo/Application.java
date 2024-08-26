package poo;

// Application.java
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2022-08-28");
            Exposition expo = new Exposition("KJC", "Korean Jewelry & Cosmetics", 10.0, false, date);
            expo.addArtist(new Artist("Franco", "Bellucci", "Italy"));
            expo.addArtist(new Artist("Heide", "De Bruyne", "Belgium"));

            // Sauvegarde de l'exposition dans le dossier 'data'
            MyUtil.saveExpoToFile(expo, "expo.xml");
            System.out.println("Exposition sauvegardée avec succès.");

            // Chargement de l'exposition depuis le fichier
            Exposition loadedExpo = MyUtil.loadExpoFromFile("expo.xml");
            System.out.println("Exposition chargée :\n" + loadedExpo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

