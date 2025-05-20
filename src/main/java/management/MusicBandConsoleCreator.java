package management;

import OrdinaryClasses.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;

public class MusicBandConsoleCreator {

    private final Scanner scanner;

    public MusicBandConsoleCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    public static boolean isDateNotLaterThanToday(Date date) {
        if (date == null) {
            return true; // Или false, если null недопустимо
        }
        LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return !inputDate.isAfter(today);
    }

    private String readName() {
        while (true) {
            System.out.print("Type name of the band: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Error, the group must have at least one word in name, please try again.");
            }
        }
    }

    private Coordinates readCoordinates() {
        Double x = null;
        while (x == null) {
            System.out.print("enter the X coordinate: ");
            try {
                x = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено некорректное число. Пожалуйста, повторите ввод.");
            }
        }

        long y = 1000;
        while (y > 860) {
            System.out.print("enter the Y coordinate (max value is 860): ");
            try {
                y = Long.parseLong(scanner.nextLine().trim());
                if (y > 860) {
                    System.out.println("Error: the value must be lower 861. Please,repeat the input.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: incorrect number entered. Please,repeat the input.");
            }
        }

        return new Coordinates(x, y);
    }

    private Long readNumberOfParticipants() {
        Long numberOfParticipants = null;
        while (numberOfParticipants == null || numberOfParticipants <= 0) {
            System.out.print("Enter the number of participants (or leave it blank): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                numberOfParticipants = Long.parseLong(input);
                if (numberOfParticipants <= 0) {
                    System.out.println("Error: The number of participants must be greater than 0. Please repeat the input.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: incorrect number entered. Please,repeat the input.");
            }
        }
        return numberOfParticipants;
    }

    private MusicGenre readGenre() {
        MusicGenre genre = null;
        while (genre == null) {
            System.out.println("Available genres: " + Arrays.toString(MusicGenre.values()));
            System.out.print("Enter the genre (or leave it blank): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                genre = MusicGenre.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: incorrect number entered. Please,repeat the input.");
            }
        }
        return genre;
    }

    private Person readFrontMan() {
        String name = null;
        while (name == null || name.isEmpty()) {
            System.out.print("Enter the name of the Frontman: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Error: The name cannot be empty. Please repeat the input.");
            }
        }

        Date birthday = null;
        while (true) {
            System.out.print("Enter the date of birth (YYYY-MM-DD, or leave it blank): ");
            String birthdayInput = scanner.nextLine().trim();
            if (birthdayInput.isEmpty()) {
                break; // Если строка пустая, оставляем birthday = null
            }
            try {
                LocalDate localDate = LocalDate.parse(birthdayInput);
                birthday = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (!isDateNotLaterThanToday(birthday)) {
                    System.out.println("Mistake: The date of birth cannot be later than today.");
                    birthday = null; // Сбрасываем значение, чтобы попросить ввод заново
                    continue;  // Возвращаемся к началу цикла (переходим к следующей итерации)
                }
                break; // Если парсинг успешен, выходим из цикла
            } catch (DateTimeParseException e) {
                System.out.println("Error: An incorrect date was entered. Please use the YYYY-MM-DD format.");
            }
        }

        Color hairColor = null;
        while (hairColor == null) {
            System.out.println("Available colors of hair " + Arrays.toString(Color.values()));
            System.out.print("Enter the color of hair: ");
            String hairColorInput = scanner.nextLine().trim();
            try {
                hairColor = Color.valueOf(hairColorInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: incorrect value entered. Please repeat the input.");
            }
        }

        Country nationality = null;
        while (true) {
            System.out.println("Available nationalities: " + Arrays.toString(Country.values()));
            System.out.print("Enter the nationality (or leave it blank): ");
            String nationalityInput = scanner.nextLine().trim();
            if (nationalityInput.isEmpty()) {
                break;
            }
            try {
                nationality = Country.valueOf(nationalityInput.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: incorrect value entered. Please repeat the input.");
            }
        }

        return Person.builder()
                .setName(name)
                .setHairColor(hairColor)
                .setBirthday(birthday)
                .setNationality(nationality)
                .build();
    }

    public MusicBand createMusicBand() {
        MusicBand.Builder builder = new MusicBand.Builder();
        builder.setName(readName());
        builder.setCoordinates(readCoordinates());
        Long numberOfParticipants = readNumberOfParticipants();
        if (numberOfParticipants != null) {
            builder.setNumberOfParticipants(numberOfParticipants);
        }
        MusicGenre genre = readGenre();
        if (genre != null) {
            builder.setGenre(genre);
        }
        Person frontMan = readFrontMan();
        if (frontMan != null) {
            builder.setFrontMan(frontMan);
        }
        return builder.build();
    }
}