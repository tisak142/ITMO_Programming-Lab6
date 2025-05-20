package xml;

import OrdinaryClasses.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;

public class MusicBandXmlConverter implements XmlConverter {

    @Override
    public Element toXmlElement(Document document, MusicBand musicBand) {
        Element bandElement = document.createElement("musicBand");

        // Добавляем элементы для каждого поля MusicBand
        appendElement(document, bandElement, "name", musicBand.getName());

        // Coordinates
        Element coordinatesElement = document.createElement("coordinates");
        bandElement.appendChild(coordinatesElement);
        appendElement(document, coordinatesElement, "x", String.valueOf(musicBand.getCoordinates().getX()));
        appendElement(document, coordinatesElement, "y", String.valueOf(musicBand.getCoordinates().getY()));


        // NumberOfParticipants (optional)
        if (musicBand.getNumberOfParticipants() != null) {
            appendElement(document, bandElement, "numberOfParticipants", String.valueOf(musicBand.getNumberOfParticipants()));
        }

        // Genre (optional)
        if (musicBand.getGenre() != null) {
            appendElement(document, bandElement, "genre", musicBand.getGenre().toString());
        }

        // FrontMan
        if (musicBand.getFrontMan() != null) {
            Element frontManElement = document.createElement("frontMan");
            bandElement.appendChild(frontManElement);
            appendElement(document, frontManElement, "name", musicBand.getFrontMan().getName());
            // ... добавьте другие поля FrontMan ...

            Element birthdayElement = document.createElement("birthday");
            frontManElement.appendChild(birthdayElement);
            //Обрабатываем null
            if(musicBand.getFrontMan().getBirthday() != null){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                appendElement(document, birthdayElement, "date", formatter.format(musicBand.getFrontMan().getBirthday()));
            }
            appendElement(document, frontManElement, "hairColor", musicBand.getFrontMan().getHairColor().toString());
            appendElement(document, frontManElement, "nationality", musicBand.getFrontMan().getNationality().toString());
            bandElement.appendChild(frontManElement);
        }

        return bandElement;
    }

    private void appendElement(Document document, Element parentElement, String tagName, String textContent) {
        if (textContent != null) {
            Element element = document.createElement(tagName);
            element.setTextContent(textContent);
            parentElement.appendChild(element);
        }
    }
}