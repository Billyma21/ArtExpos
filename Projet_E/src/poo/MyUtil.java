package poo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class MyUtil {

    private static final String DATA_FOLDER = "data";

    private static XStream getXStream() {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY); 
        xstream.allowTypes(new Class[]{Exposition.class, Artist.class, Evenement.class});
        xstream.processAnnotations(Exposition.class);
        xstream.processAnnotations(Artist.class);
        return xstream;
    }

    public static void saveExpoToFile(Exposition expo, String filename) throws IOException {
        File directory = new File(DATA_FOLDER);
        if (!directory.exists()) {
            directory.mkdir();
        }

        XStream xstream = getXStream();
        File file = new File(DATA_FOLDER, filename);
        try (FileWriter writer = new FileWriter(file)) {
            xstream.toXML(expo, writer);
        }
    }

    public static Exposition loadExpoFromFile(String filename) throws IOException {
        File file = new File(DATA_FOLDER, filename);
        if (!file.exists()) {
            throw new IOException("Le fichier n'existe pas : " + file.getAbsolutePath());
        }

        XStream xstream = getXStream();
        try (FileReader reader = new FileReader(file)) {
            return (Exposition) xstream.fromXML(reader);
        }
    }
}
