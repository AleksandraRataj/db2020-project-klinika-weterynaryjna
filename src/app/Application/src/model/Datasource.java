package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    Connection conn;

    public static final String TABLE_OWNER = "owner";
    public static final String COLUMN_OWNER_ID = "owner_id";
    public static final String COLUMN_OWNER_FIRST_NAME = "first_name";
    public static final String COLUMN_OWNER_LAST_NAME = "last_name";
    public static final String COLUMN_OWNER_ADDRESS = "address";
    public static final String COLUMN_OWNER_PHONE = "phone_number";

    public static final String TABLE_ANIMAL = "animal";
    public static final String COLUMN_ANIMAL_ID = "animal_id";
    public static final String COLUMN_ANIMAL_NAME = "name";
    public static final String COLUMN_ANIMAL_SEX = "sex";
    public static final String COLUMN_ANIMAL_BIRTH_DATE = "birth_date";
    public static final String COLUMN_ANIMAL_SPECIES = "species";
    public static final String COLUMN_ANIMAL_BREED = "breed";
    public static final String COLUMN_ANIMAL_COLOR = "color";
    public static final String COLUMN_ANIMAL_FUR = "fur";
    public static final String COLUMN_ANIMAL_OWNER_ID = "owner_id";

    public static final String TABLE_APPOINTMENT = "appointment";
    public static final String COLUMN_APPOINTMENT_ID = "appointment_id";
    public static final String COLUMN_APPOINTMENT_DATA = "data";
    public static final String COLUMN_APPOINTMENT_TIME = "time";
    public static final String COLUMN_APPOINTMENT_VETERINARIAN_ID = "veterinarian_id";
    public static final String COLUMN_APPOINTMENT_ANIMAL_ID = "animal_id";

    public static final String TABLE_VETERINARIAN = "veterinarian";
    public static final String COLUMN_VETERINARIAN_ID = "veterinarian_id";
    public static final String COLUMN_VETERINARIAN_FIRST_NAME = "first_name";
    public static final String COLUMN_VETERINARIAN_LAST_NAME = "last_name";
    public static final String COLUMN_VETERINARIAN_ADDRESS = "address";
    public static final String COLUMN_VETERINARIAN_PHONE_NUMBER = "phone_number";

    public static final String TABLE_ANIMAL_DIAGNOSIS = "animal_diagnosis";
    public static final String COLUMN_ANIMAL_DIAGNOSIS_ID = "animal_diagnosis_id";
    public static final String COLUMN_ANIMAL_DIAGNOSIS_REGIMEN = "regimen";
    public static final String COLUMN_ANIMAL_DIAGNOSIS_APPOINTMENT_ID = "appointment_id";
    public static final String COLUMN_ANIMAL_DIAGNOSIS_DIAGNOSIS_ID = "diagnosis_id";

    public static final String TABLE_DRUG_PLAN = "drug_plan";
    public static final String COLUMN_DRUG_PLAN_ID = "drug_plan_id";
    public static final String COLUMN_DRUG_PLAN_ADVICES = "advices";
    public static final String COLUMN_DRUG_PLAN_DRUG_ID = "drug_id";
    public static final String COLUMN_DRUG_PLAN_DIAGNOSIS_ID = "diagnosis_id";

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String INSERT_OWNER = "INSERT INTO " + TABLE_OWNER +
            '(' + COLUMN_OWNER_FIRST_NAME + ',' + COLUMN_OWNER_LAST_NAME + ',' + COLUMN_OWNER_ADDRESS + ',' + COLUMN_OWNER_PHONE + ") VALUES(?,?,?,?)";

    public static final String DELETE_OWNER = "DELETE FROM " + TABLE_OWNER + " WHERE " + COLUMN_OWNER_ID + " = ?";

    public static final String INSERT_ANIMAL = "INSERT INTO " + TABLE_ANIMAL +
            '(' + COLUMN_ANIMAL_NAME + ", " + COLUMN_ANIMAL_SEX + ", " + COLUMN_ANIMAL_BIRTH_DATE + ", " + COLUMN_ANIMAL_SPECIES + ", " +
            COLUMN_ANIMAL_BREED + ", " + COLUMN_ANIMAL_COLOR + ", " + COLUMN_ANIMAL_FUR + ", " + COLUMN_ANIMAL_OWNER_ID + ") VALUES(?,?,?,?,?,?,?,?)";

    public static final String DELETE_ANIMAL = "DELETE FROM " + TABLE_ANIMAL + " WHERE " + COLUMN_ANIMAL_NAME + " = ?" +
            " AND " + COLUMN_ANIMAL_OWNER_ID + " = ?";

    public static final String QUERY_ANIMALS_BY_OWNER_START =
            "SELECT " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_NAME + ", " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_SEX + ", " +
                    TABLE_ANIMAL + "." + COLUMN_ANIMAL_BIRTH_DATE + ", " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_SPECIES +
                    " FROM " + TABLE_ANIMAL + " INNER JOIN " + TABLE_OWNER + " ON " +
                    TABLE_ANIMAL + "." + COLUMN_ANIMAL_OWNER_ID + " = " + TABLE_OWNER + "." + COLUMN_OWNER_ID +
                    " WHERE " + TABLE_OWNER + "." + COLUMN_OWNER_FIRST_NAME + " = \"";

    public static final String QUERY_ANIMALS_BY_OWNER_SORT =
            " ORDER BY " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_NAME + " ";

    public static final String QUERY_ANIMAL_INFORMATION_START =
            "SELECT " + TABLE_ANIMAL + "." + "*" + " FROM " + TABLE_ANIMAL + " INNER JOIN " + TABLE_OWNER + " ON " +
                    TABLE_ANIMAL + "." + COLUMN_ANIMAL_OWNER_ID + " = " + TABLE_OWNER + "." + COLUMN_OWNER_ID +
                    " WHERE " + TABLE_OWNER + "." + COLUMN_OWNER_FIRST_NAME + " = \"";

    public static final String INSERT_APPOINTMENT = "INSERT INTO " + TABLE_APPOINTMENT +
            '(' + COLUMN_APPOINTMENT_DATA + ',' + COLUMN_APPOINTMENT_TIME + ',' + COLUMN_APPOINTMENT_VETERINARIAN_ID + "," + COLUMN_APPOINTMENT_ANIMAL_ID + ") VALUES(?,?,?,?)";

    public static final String INSERT_ANIMAL_DIAGNOSIS =
            "INSERT INTO " + TABLE_ANIMAL_DIAGNOSIS + "(" + COLUMN_ANIMAL_DIAGNOSIS_REGIMEN + ", " + COLUMN_ANIMAL_DIAGNOSIS_APPOINTMENT_ID + ", " + COLUMN_ANIMAL_DIAGNOSIS_DIAGNOSIS_ID + ") VALUES(?,?,?)";

    public static final String INSERT_DRUG_PLAN =
            "INSERT INTO " + TABLE_DRUG_PLAN + "(" + COLUMN_DRUG_PLAN_DIAGNOSIS_ID + ", " + COLUMN_DRUG_PLAN_ADVICES + ", " + COLUMN_DRUG_PLAN_DRUG_ID + ") VALUES(?,?,?)";

    public static final String UPDATE_APPOINTMENT = "UPDATE " + TABLE_APPOINTMENT + " SET " +
            COLUMN_APPOINTMENT_DATA + " = ?, " + COLUMN_APPOINTMENT_TIME + " = ?" +
            " WHERE " + COLUMN_APPOINTMENT_DATA + " = ? AND " + COLUMN_APPOINTMENT_TIME + " = ? ";

    public static final String QUERY_APPOINTMENT_FOR_OWNER = "SELECT " + TABLE_OWNER + '.' + COLUMN_OWNER_FIRST_NAME + ", " + TABLE_OWNER + "." + COLUMN_OWNER_LAST_NAME + ", " +
            TABLE_ANIMAL + "." + COLUMN_ANIMAL_NAME + ", " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_DATA + ", " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_TIME +
            " FROM " + TABLE_ANIMAL + " JOIN " + TABLE_APPOINTMENT + " ON " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_ANIMAL_ID  + " = " + TABLE_ANIMAL + '.' + COLUMN_ANIMAL_ID +
            " JOIN " + TABLE_OWNER + " ON " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_OWNER_ID + " = " + TABLE_OWNER + "." + COLUMN_OWNER_ID +
            " WHERE " + TABLE_OWNER  + "." + COLUMN_OWNER_LAST_NAME + " = \"";

    public static final String QUERY_DIAGNOSIS_FOR_ANIMAL = "SELECT " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_NAME + "," + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_DATA + "," +
            "diagnosis.name, diagnosis.description, animal_diagnosis.regimen FROM " + TABLE_ANIMAL + " INNER JOIN " + TABLE_APPOINTMENT + " ON "
            + TABLE_ANIMAL + "." + COLUMN_ANIMAL_ID + " = " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_ANIMAL_ID +
            " INNER JOIN animal_diagnosis ON animal_diagnosis.appointment_id = appointment.appointment_id " +
            "INNER JOIN diagnosis ON animal_diagnosis.animal_diagnosis_id = diagnosis.diagnosis_id " +
            "WHERE " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_NAME + " = \"";

    public static final String QUERY_APPOINTMENT_FOR_VETERINARIAN = "SELECT " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_DATA + ", " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_TIME + ", " +
            TABLE_OWNER + "." + COLUMN_OWNER_FIRST_NAME + ", " + TABLE_OWNER + "." + COLUMN_OWNER_LAST_NAME + ", " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_NAME + ", " +
            TABLE_ANIMAL + "." + COLUMN_ANIMAL_SPECIES + ", " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_BREED + ", " + TABLE_VETERINARIAN + "." + COLUMN_VETERINARIAN_FIRST_NAME + ", " +
            TABLE_VETERINARIAN + "." + COLUMN_VETERINARIAN_LAST_NAME + " FROM " + TABLE_OWNER +
            " INNER JOIN " + TABLE_ANIMAL + " ON " + TABLE_OWNER + "." + COLUMN_OWNER_ID + " = " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_OWNER_ID +
            " INNER JOIN " + TABLE_APPOINTMENT + " ON " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_ID + " = " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_ANIMAL_ID +
            " INNER JOIN " + TABLE_VETERINARIAN + " ON " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_VETERINARIAN_ID + " = " + TABLE_VETERINARIAN + "." + COLUMN_VETERINARIAN_ID +
            " WHERE " + TABLE_VETERINARIAN + "." + COLUMN_VETERINARIAN_FIRST_NAME + " = \"";

    private PreparedStatement insertIntoOwner;
    private PreparedStatement deleteFromOwner;
    private PreparedStatement insertIntoAnimal;
    private PreparedStatement deleteFromAnimal;
    private PreparedStatement insertIntoAppointment;
    private PreparedStatement insertIntoAnimalDiagnosis;
    private PreparedStatement insertIntoDrugPlan;
    private PreparedStatement updateAppointment;

    public boolean open() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/vet_clinic?useUnicode=true" +
                    "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            insertIntoOwner = conn.prepareStatement(INSERT_OWNER, Statement.RETURN_GENERATED_KEYS);
            deleteFromOwner = conn.prepareStatement(DELETE_OWNER);
            insertIntoAnimal = conn.prepareStatement(INSERT_ANIMAL);
            deleteFromAnimal = conn.prepareStatement(DELETE_ANIMAL);
            insertIntoAppointment = conn.prepareStatement(INSERT_APPOINTMENT);
            insertIntoDrugPlan = conn.prepareStatement(INSERT_DRUG_PLAN);
            insertIntoAnimalDiagnosis = conn.prepareStatement(INSERT_ANIMAL_DIAGNOSIS);
            updateAppointment = conn.prepareStatement(UPDATE_APPOINTMENT);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't open connection: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (insertIntoOwner != null) {
                insertIntoOwner.close();
            }
            if (deleteFromOwner != null) {
                deleteFromOwner.close();
            }
            if (insertIntoAnimal != null) {
                insertIntoAnimal.close();
            }
            if (deleteFromAnimal != null) {
                deleteFromAnimal.close();
            }
            if (insertIntoAppointment != null) {
                insertIntoAppointment.close();
            }
            if (insertIntoAnimalDiagnosis != null) {
                insertIntoAnimalDiagnosis.close();
            }
            if (insertIntoDrugPlan != null) {
                insertIntoDrugPlan.close();
            }
            if (updateAppointment != null) {
                updateAppointment.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Owner> queryOwner(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_OWNER);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_OWNER_LAST_NAME);

            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
            } else {
                sb.append(" ASC");
            }
        }

//        System.out.println("SQL Statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Owner> owners = new ArrayList<>();
            while (results.next()) {
                Owner owner = new Owner();
                owner.setOwner_id(results.getInt(1));
                owner.setFirst_name(results.getString(2));
                owner.setLast_name(results.getString(3));
                owner.setAddress(results.getString(4));
                owner.setPhone_number(results.getInt(5));
                owners.add(owner);
            }

            return owners;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertOwner(String name, String surname, String address, int phone) {
        try {
            insertIntoOwner.setString(1, name);
            insertIntoOwner.setString(2, surname);
            insertIntoOwner.setString(3, address);
            insertIntoOwner.setInt(4, phone);

            insertIntoOwner.executeUpdate();
            System.out.println("\nDodanie przebiegło pomyślnie!\n");
        } catch (SQLException e) {
            System.out.println("\nNie udało się dodać właściciela!\n");
        }
    }

    public void deleteFromOwner(int id) {
        try {
            deleteFromOwner.setInt(1, id);
            deleteFromOwner.executeUpdate();
            System.out.println("\nUsunięcie przebiegło pomyślnie\n");
        } catch (SQLException e) {
            System.out.println("\nNie udało się usunąć właściciela!\n");
        }
    }

    public List<Animal> queryAnimal(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ANIMAL);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ANIMAL_NAME);

            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
            } else {
                sb.append(" ASC");
            }
        }

//        System.out.println("SQL Statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Animal> animals = new ArrayList<>();
            while (results.next()) {
                Animal animal = new Animal();
                animal.setAnimal_id(results.getInt(1));
                animal.setName(results.getString(2));
                animal.setSex(results.getString(3));
                animal.setBirth_date(results.getDate(4));
                animal.setSpecies(results.getString(5));
                animal.setBreed(results.getString(6));
                animal.setColor(results.getString(7));
                animal.setFur(results.getString(8));
                animal.setOwner_id(results.getInt(9));
                animals.add(animal);
            }
            return animals;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertAnimal(String name, String sex, Date birthDate, String species, String breed, String color,
                             String fur, int ownerId) {
        try {
            List<Owner> owners = this.queryOwner(1);
            boolean isOwner = false;

            for (Owner owner : owners) {
                if (owner.getOwner_id() == ownerId) {
                    isOwner = true;
                    break;
                }
            }

            if (isOwner) {
                insertIntoAnimal.setString(1, name);
                insertIntoAnimal.setString(2, sex);
                insertIntoAnimal.setDate(3, birthDate);
                insertIntoAnimal.setString(4, species);
                insertIntoAnimal.setString(5, breed);
                insertIntoAnimal.setString(6, color);
                insertIntoAnimal.setString(7, fur);
                insertIntoAnimal.setInt(8, ownerId);

                insertIntoAnimal.executeUpdate();
                System.out.println("\nDodanie przebiegło pomyślnie!\n");
            } else {
                System.out.println("Nie udało znaleźć się właściciela o takim ID: " + ownerId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nNie udało się dodać zwierzęcia!\n");
        }
    }

    public void deleteFromAnimal(String name, int ownerId) {
        try {
            deleteFromAnimal.setString(1, name);
            deleteFromAnimal.setInt(2, ownerId);
            deleteFromAnimal.executeUpdate();
            System.out.println("\nUsunięcie przebiegło pomyślnie\n");
        } catch (SQLException e) {
            System.out.println("\nNie udało się usunąć zwierzęcia!\n");
        }
    }

    public List<Animal> queryAnimalsByOwner(String ownerFirstName, String ownerLastName, int sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_ANIMALS_BY_OWNER_START);
        sb.append(ownerFirstName);
        sb.append("\"");
        sb.append(" AND " + TABLE_OWNER + "." + COLUMN_OWNER_LAST_NAME + " = \"");
        sb.append(ownerLastName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ANIMALS_BY_OWNER_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

//        System.out.println("SQL Statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<Animal> animals = new ArrayList<>();
            while (resultSet.next()) {

                Animal animal = new Animal();
                animal.setName(resultSet.getString(1));
                animal.setSex(resultSet.getString(2));
                animal.setBirth_date(resultSet.getDate(3));
                animal.setSpecies(resultSet.getString(4));

                animals.add(animal);
            }

            return animals;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Veterinarian> queryVeterinarian(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_VETERINARIAN);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_VETERINARIAN_LAST_NAME);

            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
            } else {
                sb.append(" ASC");
            }
        }

//        System.out.println("SQL Statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Veterinarian> veterinarians = new ArrayList<>();
            while (results.next()) {
                Veterinarian veterinarian = new Veterinarian();
                veterinarian.setVeterinarian_id(results.getInt(1));
                veterinarian.setFirst_name(results.getString(2));
                veterinarian.setLast_name(results.getString(3));
                veterinarian.setAddress(results.getString(4));
                veterinarian.setPhone_number(results.getInt(5));
                veterinarians.add(veterinarian);
            }

            return veterinarians;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Appointment> queryAppointment(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_APPOINTMENT);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_APPOINTMENT_DATA);
            sb.append(" AND ");
            sb.append(COLUMN_APPOINTMENT_TIME);

            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
            } else {
                sb.append(" ASC");
            }
        }

//        System.out.println("SQL Statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Appointment> appointments = new ArrayList<>();
            while (results.next()) {

                Appointment appointment = new Appointment();
                appointment.setAppointment_id(results.getInt(1));
                appointment.setData(results.getDate(2));
                appointment.setTime(results.getTime(3));
                appointment.setAnimal_id(results.getInt(4));
                appointment.setVeterinarian_id(results.getInt(5));

                appointments.add(appointment);
            }

            return appointments;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertAppointment(Date date, Time time, int veterinarian_id, int animal_id) {
        try {
            List<Animal> animals = this.queryAnimal(3);
            boolean isAnimal = false;

            for (Animal animal : animals) {
                if (animal.animal_id == animal_id) {
                    isAnimal = true;
                    break;
                }
            }

            List<Veterinarian> veterinarians = this.queryVeterinarian(3);
            boolean isVeterinarian = false;

            for (Veterinarian veterinarian : veterinarians) {
                if (veterinarian.getVeterinarian_id() == veterinarian_id) {
                    isVeterinarian = true;
                    break;
                }
            }

            List<Appointment> appointments = this.queryAppointment(3);
            boolean isAvailable = true;

            for (Appointment appointment : appointments) {
                if (appointment.getData().equals(date) && (appointment.getTime().getHours() == time.getHours())) {
                    isAvailable = false;
                }
            }

            if (isAnimal && isVeterinarian && isAvailable) {
                insertIntoAppointment.setDate(1, date);
                insertIntoAppointment.setTime(2, time);
                insertIntoAppointment.setInt(3, veterinarian_id);
                insertIntoAppointment.setInt(4, animal_id);

                insertIntoAppointment.executeUpdate();
                System.out.println("Dodanie wizyty przebiegło pomyślnie!");
            } else if (!isAvailable) {
                System.out.println("Nie ma takiego wolnego terminu!");
            } else {
                System.out.println("Nie ma weterynarza lub zwierzęcia o takim ID!");
            }
        } catch (SQLException e) {
            System.out.println("Nie udało się dodać wizyty!");
        }
    }

    public void updateAppointment(Date newDate, Time newTime, Date date, Time time) {
        try {
            List<Appointment> appointments = this.queryAppointment(3);
            boolean isAvailable = true;

            for(Appointment appointment : appointments) {
                if(appointment.getData().equals(newDate) && (appointment.getTime().getHours() == newTime.getHours())) {
                    isAvailable = false;
                }
            }

            if (isAvailable) {
                updateAppointment.setDate(1,newDate);
                updateAppointment.setTime(2,newTime);
                updateAppointment.setDate(3,date);
                updateAppointment.setTime(4,time);

                updateAppointment.executeUpdate();
                System.out.println("Modyfikacja wizyty pobiegła pomyślnie!");
                System.out.println("Nowy termin: " + newDate + " " + newTime);

            } else {
                System.out.println("Nie ma takiego wolnego terminu!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Animal queryAnimalInformation(String ownerName, String ownerSurname, String animalName) {

        StringBuilder sb = new StringBuilder(QUERY_ANIMAL_INFORMATION_START);
        sb.append(ownerName);
        sb.append("\"");
        sb.append(" AND " + TABLE_OWNER + "." + COLUMN_OWNER_LAST_NAME + " = \"");
        sb.append(ownerSurname);
        sb.append("\"");
        sb.append(" AND " + TABLE_ANIMAL + "." + COLUMN_ANIMAL_NAME + " = \"");
        sb.append(animalName);
        sb.append("\"");

//        System.out.println("SQL Statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            Animal animal = new Animal();
            while (resultSet.next()) {

                animal.setAnimal_id(resultSet.getInt(1));
                animal.setName(resultSet.getString(2));
                animal.setSex(resultSet.getString(3));
                animal.setBirth_date(resultSet.getDate(4));
                animal.setSpecies(resultSet.getString(5));
                animal.setBreed(resultSet.getString(6));
                animal.setColor(resultSet.getString(7));
                animal.setFur(resultSet.getString(8));
                animal.setOwner_id(resultSet.getInt(9));
            }

            return animal;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertAnimalDiagnosis(String regimen, int appointmentId, int diagnosisId) {
        try {
            insertIntoAnimalDiagnosis.setString(1, regimen);
            insertIntoAnimalDiagnosis.setInt(2, appointmentId);
            insertIntoAnimalDiagnosis.setInt(3, diagnosisId);

            insertIntoAnimalDiagnosis.executeUpdate();
            System.out.println("Dodanie diagnozy przebiegło pomyślnie!");
        } catch (SQLException e) {
            System.out.println("Nie udało się dodać diagnozy!");
        }
    }

    public void insertDrugPlan(int diagnosisId, String advices, int drugId) {
        try {
            insertIntoDrugPlan.setInt(1, diagnosisId);
            insertIntoDrugPlan.setString(2, advices);
            insertIntoDrugPlan.setInt(3, drugId);

            insertIntoDrugPlan.executeUpdate();
            System.out.println("Dodanie leku do recepty przebiegło pomyślnie!");
        } catch (SQLException e) {
            System.out.println("Nie udało się dodać leku do recepty!");
        }
    }

    public List<AppointmentForOwner> queryAppointmentForOwner(String last_name) {

        StringBuilder sb = new StringBuilder(QUERY_APPOINTMENT_FOR_OWNER);
        sb.append(last_name);
        sb.append("\"");

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<AppointmentForOwner> appointmentForOwners = new ArrayList<>();
            while (resultSet.next()) {

                AppointmentForOwner appointmentForOwner = new AppointmentForOwner();
                appointmentForOwner.setFirst_name(resultSet.getString(1));
                appointmentForOwner.setLast_name(resultSet.getString(2));
                appointmentForOwner.setAnimal(resultSet.getString(3));
                appointmentForOwner.setDate(resultSet.getDate(4));
                appointmentForOwner.setTime(resultSet.getTime(5));

                appointmentForOwners.add(appointmentForOwner);
            }

            return appointmentForOwners;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<DiagnosisForAnimal> queryDiagnosisForAnimal(String animal_name) {

        StringBuilder sb = new StringBuilder(QUERY_DIAGNOSIS_FOR_ANIMAL);
        sb.append(animal_name);
        sb.append("\"");

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<DiagnosisForAnimal> diagnosisForAnimals = new ArrayList<>();
            while (resultSet.next()) {

                DiagnosisForAnimal diagnosisForAnimal = new DiagnosisForAnimal();
                diagnosisForAnimal.setAnimal_name(resultSet.getString(1));
                diagnosisForAnimal.setData(resultSet.getDate(2));
                diagnosisForAnimal.setDiagnosis_name(resultSet.getString(3));
                diagnosisForAnimal.setDescription(resultSet.getString(4));
                diagnosisForAnimal.setRegimen(resultSet.getString(5));

                diagnosisForAnimals.add(diagnosisForAnimal);
            }

            return diagnosisForAnimals;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<AppointmentForVeterinarian> queryAppointmentForVeterinarian(String first_name,String last_name, Date date) {
        StringBuilder sb = new StringBuilder(QUERY_APPOINTMENT_FOR_VETERINARIAN);
        sb.append(first_name);
        sb.append("\" AND ");
        sb.append(TABLE_VETERINARIAN + "." + COLUMN_VETERINARIAN_LAST_NAME + " = \"");
        sb.append(last_name);
        sb.append("\" AND ");
        sb.append(TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_DATA + " = \"");
        sb.append(date);
        sb.append("\"");

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<AppointmentForVeterinarian> appointmentForVeterinarians = new ArrayList<>();
            while (resultSet.next()) {

                AppointmentForVeterinarian appointmentForVeterinarian = new AppointmentForVeterinarian();
                appointmentForVeterinarian.setDate(resultSet.getDate(1));
                appointmentForVeterinarian.setTime(resultSet.getTime(2));
                appointmentForVeterinarian.setOwner_first_name(resultSet.getString(3));
                appointmentForVeterinarian.setOwner_last_name(resultSet.getString(4));
                appointmentForVeterinarian.setAnimal_name(resultSet.getString(5));
                appointmentForVeterinarian.setAnimal_species(resultSet.getString(6));
                appointmentForVeterinarian.setAnimal_breed(resultSet.getString(7));
                appointmentForVeterinarian.setVeterinarian_first_name(resultSet.getString(8));
                appointmentForVeterinarian.setVeterinarian_last_name(resultSet.getString(9));

                appointmentForVeterinarians.add(appointmentForVeterinarian);
            }

            return appointmentForVeterinarians;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
