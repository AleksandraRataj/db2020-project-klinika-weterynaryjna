package model;


import java.sql.Date;

public class Animal {

    int animal_id;
    String name;
    String sex;
    Date birth_date;
    String species;
    String breed;
    String color;
    String fur;
    int owner_id;

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFur() {
        return fur;
    }

    public void setFur(String fur) {
        this.fur = fur;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString()
    {
        return "ID " + animal_id +
                ", imię = " + name +
                ", płeć = " + sex +
                ", data urodzenia = " + birth_date +
                ", gatunek = " + species +
                ", rasa = " + breed +
                ", kolor = " + color +
                ", sierść = " + fur +
                ", ID właściciela =" + owner_id;
    }
}
