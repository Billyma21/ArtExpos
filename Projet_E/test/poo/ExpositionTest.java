package poo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpositionTest {

    private Exposition exposition;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() throws Exception {
        Date date = sdf.parse("2022-08-28");
        exposition = new Exposition("KJC", "Korean Jewelry & Cosmetics", 10.0, false, date);
        exposition.addArtist(new Artist("Franco", "Bellucci", "Italy"));
        exposition.addArtist(new Artist("Heide", "De Bruyne", "Belgium"));
    }

    @Test
    public void testToString() {
        String expectedOutput = "**************************************\n" +
                "* Exposition : KJC\n" +
                "* Date : 2022-08-28\n" +
                "* Description : Korean Jewelry & Cosmetics\n" +
                "* Artistes : Franco Bellucci, Heide De Bruyne\n" +
                "* Prix : 10,00 €\n" +
                "* Mode : en présentiel\n" +
                "**************************************";

        assertEquals(expectedOutput, exposition.toString());
    }

    @Test
    public void testComputePromo() {
        assertEquals(9.0, exposition.computePromo(10), 0.001);
        assertEquals(8.0, exposition.computePromo(20), 0.001);
    }

    @Test
    public void testNegativePriceException() {
        assertThrows(NegativePriceException.class, () -> {
            new Exposition("Negative Test", "Test Desc", -10.0, false, new Date());
        });
    }

    @Test
    public void testSearchArtistsByCountry() {
        assertEquals(1, exposition.searchArtistsByCountry("Belgium").size());
        assertEquals(1, exposition.searchArtistsByCountry("Italy").size());
        assertEquals(0, exposition.searchArtistsByCountry("France").size());
    }

    @Test
    public void testFreeExposition() throws Exception {
        Date date = sdf.parse("2024-08-26");
        Exposition freeExpo = new Exposition("Free Expo", "Description gratuite", 0.0, true, date);
        freeExpo.addArtist(new Artist("Daewon", "Sang", "Korea"));
        String expectedOutput = "**************************************\n" +
                "* Exposition : Free Expo\n" +
                "* Date : 2024-08-26\n" +
                "* Description : Description gratuite\n" +
                "* Artistes : Daewon Sang\n" +
                "* Prix : Gratuit\n" +
                "* Mode : en ligne\n" +
                "**************************************";

        assertEquals(expectedOutput, freeExpo.toString());
    }
}
