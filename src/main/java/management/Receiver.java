package management;

import OrdinaryClasses.MusicBand;
import OrdinaryClasses.MusicGenre;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

    public void clearCollection() {
        bands.clear();
    }

    public void addIfMax(MusicBand band) {
        boolean flag = true;
        for (MusicBand b : bands) {
            if (band.compareTo(b) <= 0) {
                flag = false;
                System.out.println("The band " + band.getName() + " wasn't added to collection");
                break;
            }
        }
        if (flag) {
            bands.add(band);
            System.out.println("Added Music Band - " + band.getName());
        }
    }

    public void addIfMin(MusicBand band) {
        boolean flag = true;
        for (MusicBand b : bands) {
            if (band.compareTo(b) >= 0) {
                flag = false;
                System.out.println("The band " + band.getName() + " wasn't added to collection");
                break;
            }
        }
        if (flag) {
            bands.add(band);
            System.out.println("Added Music Band - " + band.getName());
        }
    }

    public void shuffle() {
        Collections.shuffle(bands);
    }

    public float getAverageNumberOfParticipants() {
        float counter = 0;
        for (MusicBand band : bands) {
            if (band.getNumberOfParticipants() != null) {
                counter += band.getNumberOfParticipants();
            }
        }
        return counter / bands.size();
    }

    public void maxByGenre() {
        if (bands.isEmpty()) {
            System.out.println("The collection is empty.");
            return;
        }

        MusicBand maxBand = null;
        MusicGenre maxGenre = null;

        // Находим первую группу с жанром для инициализации
        for (MusicBand band : bands) {
            if (band.getGenre() != null) {
                maxBand = band;
                maxGenre = band.getGenre();
                break; // Выходим из цикла, нашли первую группу с жанром
            }
        }

        // Если не нашли ни одной группы с жанром
        if (maxBand == null) {
            System.out.println("No music bands with genre found in the collection.");
            return;
        }

        // Ищем группу с максимальным жанром
        for (MusicBand band : bands) {
            if (band.getGenre() != null && band.getGenre().toString().compareTo(maxGenre.toString()) > 0) {
                maxBand = band;
                maxGenre = band.getGenre();
            }
        }
        System.out.println("Music band with maximum genre: " + maxBand);
    }

    public HashMap<String, Integer> countByName() {
        HashMap<String, Integer> map = new HashMap<>();
        for (MusicBand band : bands) {
            map.put(band.getName(), map.getOrDefault(band.getName(), 0) + 1);
        }
        return map;
    }

    public ArrayList<MusicBand> getBands() {
        return bands;
    }

}
