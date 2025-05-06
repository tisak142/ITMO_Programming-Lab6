package da;

import OrdinaryClasses.MusicBand;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Receiver {
    private final ArrayList<MusicBand> bands; // Коллекция для хранения объектов MusicBand.
    private final LocalDate date;
    public Receiver(ArrayList<MusicBand> bands) {
        this.bands = bands;
        if (bands != null && !bands.isEmpty()) {
            System.out.println("Successfully loaded music bands into collection");
        } else {
            System.out.println("Failed to load music bands into da.Receiver.");
        }
        this.date = LocalDate.now();
    }

    public String getCollectionType() {
        return bands.getClass().getSimpleName();
    }

    public String getDate() {
        return date.toString();
    }

    public int getNumBands() {
        return bands.size();
    }

    public void showBands() {
        for (MusicBand band : bands) {
            System.out.println(band);
        }
    }

    public void add(MusicBand band) {
        bands.add(band);
    }

    public boolean containId(int id) {
        for (MusicBand band : bands) {
            if (band.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void updateId(int id, MusicBand band) {
        for (MusicBand b : bands) {
            if (b.getId() == id) {
                int index = bands.indexOf(b);
                MusicBand.Builder builder = new MusicBand.Builder();
                builder.setName(band.getName()).
                        setId(id).
                        setCoordinates(band.getCoordinates()).
                        setFrontMan(band.getFrontMan()).
                        setGenre(band.getGenre()).
                        setNumberOfParticipants(band.getNumberOfParticipants());
                MusicBand newBand = builder.build();
                bands.set(index, newBand);
            }
        }
    }

    public void removeById(int id) {
        Iterator<MusicBand> iterator = bands.iterator();
        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            if (band.getId() == id) {
                iterator.remove(); // Безопасное удаление
            }
        }
    }

}
