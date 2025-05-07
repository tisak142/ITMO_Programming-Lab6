package OrdinaryClasses;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MusicBand implements Comparable<MusicBand> {
    private final Integer id;                // Автогенерация, > 0, уникальное
    private final String name;               // Не null, не пустое
    private final Coordinates coordinates;   // Не null
    private final Date creationDate;         // Автогенерация, не null
    private final Long numberOfParticipants; // Может быть null, > 0
    private final MusicGenre genre;          // Может быть null
    private final Person frontMan;           // Не null

    // Приватный конструктор
    private MusicBand(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.creationDate = builder.creationDate;
        this.numberOfParticipants = builder.numberOfParticipants;
        this.genre = builder.genre;
        this.frontMan = builder.frontMan;
    }

    @Override
    public int compareTo(MusicBand o) {
        Integer len1 = name.length();
        Integer len2 = o.name.length();
        return len1.compareTo(len2);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private static final AtomicInteger idGenerator = new AtomicInteger(1);

        private Integer id = idGenerator.getAndIncrement(); // Автогенерация
        private String name;
        private Coordinates coordinates;
        private Date creationDate = new Date(); // Текущая дата
        private Long numberOfParticipants;
        private MusicGenre genre;
        private Person frontMan;

        // Обязательные поля
        public Builder setName(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            return this;
        }

        public Builder setCoordinates(Coordinates coordinates) {
            this.coordinates = Objects.requireNonNull(coordinates, "Coordinates cannot be null");
            return this;
        }

        public Builder setFrontMan(Person frontMan) {
            this.frontMan = Objects.requireNonNull(frontMan, "FrontMan cannot be null");
            return this;
        }

        // Необязательные поля
        public Builder setNumberOfParticipants(Long number) {
            if (number != null && number <= 0) {
                throw new IllegalArgumentException("Number of participants must be > 0");
            }
            this.numberOfParticipants = number;
            return this;
        }

        public Builder setGenre(MusicGenre genre) {
            this.genre = genre;
            return this;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public MusicBand build() {
            // Проверка обязательных полей
            if (name == null) {
                throw new IllegalStateException("Name must be set");
            }

            if (coordinates == null) {
                throw new IllegalStateException("Сoordinates must be set");
            }

            if (frontMan == null) {
                throw new IllegalStateException("FrontMan must be set");
            }

            // Дополнительная проверка для автоматических полей
            if (id == null || id <= 0) {
                throw new IllegalStateException("Invalid generated ID");
            }
            if (creationDate == null) {
                throw new IllegalStateException("Invalid creation date");
            }

            return new MusicBand(this);
        }
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", genre=" + genre +
                ", frontMan=" + frontMan +
                '}';
    }
}