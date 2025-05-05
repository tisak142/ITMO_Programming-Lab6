package OrdinaryClasses;

import java.util.Date;
import java.util.Objects;

public class Person {
    private String name;
    private Date birthday;
    private Color hairColor;
    private Country nationality;


    private Person(PersonBuilder personBuilder) {
        this.birthday = personBuilder.birthday;
        this.hairColor = personBuilder.hairColor;
        this.name = personBuilder.name;
        this.nationality = personBuilder.nationality;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {
        private String name;
        private Date birthday;
        private Color hairColor;
        private Country nationality;

        public PersonBuilder name(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            return this;
        }

        public PersonBuilder setHairColor(Color hairColor) {
            this.hairColor = Objects.requireNonNull(hairColor, "Hair color cannot be null");
            return this;
        }

        public PersonBuilder setBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }


        public PersonBuilder setNationality (Country nationality) {
            this.nationality = nationality;
            return this;
        }

        public Person build() {
            if (name == null || hairColor == null) {
                throw new IllegalStateException("Name and hair color must be set");
            }
            return new Person(this);
        }

        @Override
        public String toString() {
            return "PersonBuilder{" +
                    "name='" + name + '\'' +
                    ", birthday=" + birthday +
                    ", hairColor=" + hairColor +
                    ", nationality=" + nationality +
                    '}';
        }
    }

    public String getName() {
        return name;
    }

    public Country getNationality() {
        return nationality;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }
}
