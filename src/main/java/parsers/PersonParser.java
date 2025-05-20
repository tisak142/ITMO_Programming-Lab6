package parsers;

import OrdinaryClasses.Color;
import OrdinaryClasses.Country;
import OrdinaryClasses.Person;
import xml.XmlUtils;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PersonParser {

    public static Person parse(Element element) throws ParseException {
        Person.PersonBuilder builder = new Person.PersonBuilder(); // Используем Person.builder()

        // Парсинг имени
        String name = XmlUtils.getElementValue(element, "frontManName");
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Error: Person name cannot be null or empty");
            return null;
        } else {
            builder.setName(name); // Используем builder.name()
        }

        // Парсинг даты рождения (необязательное поле)
        String birthdayStr = XmlUtils.getElementValue(element, "birthday");
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(birthdayStr, formatter);
                Date birthday = java.sql.Date.valueOf(localDate);

                builder.setBirthday(birthday);
            } catch (Exception e) {
                System.err.println("Error: Invalid birthday format");
                return null;
            }
        }

        // Парсинг цвета волос (необязательное поле)
        String hairColorStr = XmlUtils.getElementValue(element, "hairColor");
        if (hairColorStr != null && !hairColorStr.isEmpty()) {
            try {
                Color hairColor = Color.valueOf(hairColorStr);
                builder.setHairColor(hairColor);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: Invalid hair color: " + hairColorStr);
                return null;
            }
        }

        // Парсинг национальности (необязательное поле)
        String nationalityStr = XmlUtils.getElementValue(element, "nationality");
        if (nationalityStr != null && !nationalityStr.isEmpty()) {
            try {
                Country nationality = Country.valueOf(nationalityStr);
                builder.setNationality(nationality);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: Invalid nationality: " + nationalityStr);
                return null;
            }
        }

        try {
            return builder.build();
        } catch (IllegalStateException e) {
            System.err.println("Error building Person: " + e.getMessage());
            return null;
        }
    }
}