import OrdinaryClasses.MusicBand;

import java.util.ArrayList;

public class Receiver {
    private final ArrayList<MusicBand> bands; // Коллекция для хранения объектов MusicBand.

    public Receiver(ArrayList<MusicBand> bands) {
        this.bands = bands;
        if (bands != null && !bands.isEmpty()) {
            System.out.println("Successfully loaded music bands into Receiver!");
        } else {
            System.out.println("Failed to load music bands into Receiver.");
        }
    }

}
