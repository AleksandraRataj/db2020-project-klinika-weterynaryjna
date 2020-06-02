package model;

import java.sql.Date;

public class DiagnosisForAnimal {

    private String animal_name;
    private Date data;
    private String diagnosis_name;
    private String description;
    private String regimen;

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDiagnosis_name() {
        return diagnosis_name;
    }

    public void setDiagnosis_name(String diagnosis_name) {
        this.diagnosis_name = diagnosis_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    @Override
    public String toString() {
        return "Imię zwierzęcia: " + this.getAnimal_name() + "\nData: " + this.getData() +
                "\nNazwa: " + this.getDiagnosis_name() + "\nOpis: " + this.getDescription() + "\nReżim: " + this.getRegimen();
    }
}
