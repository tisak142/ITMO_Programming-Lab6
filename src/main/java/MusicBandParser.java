import OrdinaryClasses.Coordinates;
import OrdinaryClasses.MusicBand;
import OrdinaryClasses.MusicGenre;
import OrdinaryClasses.Person;
import org.w3c.dom.Element;

import java.text.ParseException;

public class MusicBandParser {

    public static MusicBand parse(Element element) throws ParseException {
        MusicBand.Builder builder = new MusicBand.Builder();
        // Парсинг названия
        String name = XmlUtils.getElementValue(element, "name");
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Error: Name cannot be null or empty");
            return null;
        } else {
            builder.setName(name);
        }

        // Парсинг координат
        Element coordinatesElement = (Element) element.getElementsByTagName("coordinates").item(0);
        if (coordinatesElement == null) {
            System.err.println("Error: Coordinates element is missing");
            return null;
        }
        String xStr = XmlUtils.getElementValue(coordinatesElement, "x");
        String yStr = XmlUtils.getElementValue(coordinatesElement, "y");

        //проверка на null
        if (xStr == null || xStr.trim().isEmpty() || yStr == null || yStr.trim().isEmpty()){
            //проверка, чтобы координаты не были null
            System.err.println("Error: Coordinates can't be null");
            return null;
        }
        else {
            try {
                double x = Double.parseDouble(xStr);
                long y = Long.parseLong(yStr);
                Coordinates coordinates = new Coordinates(x, y);
                builder.setCoordinates(coordinates);
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid format for coordinates");
                return null;
            }
        }

        // Парсинг количества участников (необязательное поле)
        String numberOfParticipantsStr = XmlUtils.getElementValue(element, "numberOfParticipants");
        if (numberOfParticipantsStr != null && !numberOfParticipantsStr.isEmpty()) {
            try {
                long numberOfParticipants = Long.parseLong(numberOfParticipantsStr);
                builder.setNumberOfParticipants(numberOfParticipants);
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number of participants format");
                return null;
            }
        }

        // Парсинг жанра (необязательное поле)
        String genreStr = XmlUtils.getElementValue(element, "genre");
        if (genreStr != null && !genreStr.isEmpty()) {
            try {
                MusicGenre genre = MusicGenre.valueOf(genreStr);
                builder.setGenre(genre);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: Invalid genre: " + genreStr);
                return null;
            }
        }

        // Парсинг фронтмена
        Element frontManElement = (Element) element.getElementsByTagName("frontMan").item(0);
        if (frontManElement == null) {
            System.err.println("Error: FrontMan element is missing");
            return null;
        }
        Person frontMan = PersonParser.parse(frontManElement);
        if (frontMan == null){
            return null;
        }
        builder.setFrontMan(frontMan);

        try {
            return builder.build();
        } catch (IllegalStateException e) {
            System.err.println("Error building MusicBand: " + e.getMessage());
            return null;
        }
    }
}