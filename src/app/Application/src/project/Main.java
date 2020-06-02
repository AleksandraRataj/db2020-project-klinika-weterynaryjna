package project;

import model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static Datasource datasource = new Datasource();

    public static void main(String[] args) {

        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        boolean run = true;
        while (run) {
            System.out.println("Opcje zalogowania: \n" +
                    "1. Właściciel. \n" +
                    "2. Weterynarz. \n" +
                    "3. Administrator. \n" +
                    "0. Wyjście. \n");

            System.out.print("Podaj swój wybór: ");
            int wybor = scan.nextInt();
            System.out.println();

            switch (wybor) {

                case 1:

                    System.out.println("Właściciel\n");
                    System.out.println("Podaj dane do logowania: ");
                    System.out.print("Imie: ");
                    String ownerName = scan.next();
                    System.out.print("Naziwsko: ");
                    String ownerSurname = scan.next();
                    System.out.print("Numer telefonu: ");
                    int phoneNumber = scan.nextInt();

                    boolean isCorrect = false;
                    List<Owner> owners = datasource.queryOwner(3);
                    for (Owner owner : owners) {
                        if (owner.getFirst_name().equals(ownerName) && owner.getLast_name().equals(ownerSurname) && (owner.getPhone_number() == phoneNumber)) {
                            isCorrect = true;
                        }
                    }

                    if (isCorrect) {
                        Main.functionOwner(ownerName, ownerSurname);
                    } else {
                        System.out.println("Podano nieprawidłowe dane!");
                    }

                    break;

                case 2:

                    System.out.println("Weterynarz\n");
                    System.out.println("Podaj dane do logowania: ");
                    System.out.print("Imie: ");
                    String vetName = scan.next();
                    System.out.print("Nazwisko: ");
                    String vetSurname = scan.next();
                    System.out.print("Numer telefonu: ");
                    int vetPhoneNumber = scan.nextInt();

                    isCorrect = false;
                    List<Veterinarian> veterinarians = datasource.queryVeterinarian(3);
                    for (Veterinarian veterinarian : veterinarians) {
                        if (veterinarian.getFirst_name().equals(vetName) && veterinarian.getLast_name().equals(vetSurname) && (veterinarian.getPhone_number() == vetPhoneNumber)) {
                            isCorrect = true;
                        }
                    }

                    if (isCorrect) {
                        Main.functionVet(vetName, vetSurname);
                    } else {
                        System.out.println("Podano nieprawidłowe dane!");
                    }

                    break;

                case 3:

                    System.out.println("Administrator\n");
                    System.out.println("Podaj dane do logowania: ");
                    System.out.print("Login: ");
                    String adminLogin = scan.next();
                    System.out.print("Haslo: ");
                    String adminPassword = scan.next();

                    if (adminLogin.equals("admin") && adminPassword.equals("admin")) {
                        System.out.println("Udało się zalogować!");
                        Main.functionAdmin();
                    } else {
                        System.out.println("Podano nieprawidłowe dane!!!");
                    }

                    break;
                case 0:
                    System.out.println("Wyjście\n");
                    run = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór!\n");
                    break;
            }
        }
    }

    public static void functionOwner(String ownerFirstName, String ownerLastName) {

        List<AppointmentForOwner> ownerAppointments = datasource.queryAppointmentForOwner(ownerLastName);
        List<Animal> ownerAnimals = datasource.queryAnimalsByOwner(ownerFirstName, ownerLastName, 3);

        while (true) {
            System.out.println("\nOpcje Właściciela: \n" +
                    "1. Wypisanie wszsytkich zwierząt właściciela. \n" +
                    "2. Wypisanie informacji szczegółowych o zwierzęciu właściciela. \n" +
                    "3. Wypisanie wszystkich wizyt dla właściciela. \n" +
                    "4. Wypisanie diagnoz dla wybranego zwierzęcia. \n" +
                    "0. Wyjście. \n");

            System.out.print("Podaj swój wybór: ");

            int wybor = -1;
            try {
                wybor = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nPodaj prawidłowy numer!!!\n");
                scan.next();
            }

            switch (wybor) {
                case 1:
                    System.out.println("\nWypisanie wszsytkich zwierząt właściciela: \n");

                    if (ownerAnimals == null) {
                        System.out.println("Nie udało się odnaleźć zwierząt dla właściciela: " + ownerFirstName + " " + ownerLastName);
                        return;
                    }

                    for (Animal animal : ownerAnimals) {
                        System.out.println(animal.getName() + "\t" + animal.getSex() + "\t" + animal.getBirth_date() + "\t" +
                                animal.getSpecies());
                    }

                    break;

                case 2:
                    System.out.println("\nWypisanie informacji szczegółowych o zwierzęciu właściciela: \n");

                    String animalName;

                    try {
                        System.out.print("Imię zwierzęcia: ");
                        animalName = scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    Animal animal = datasource.queryAnimalInformation(ownerFirstName, ownerLastName, animalName);
                    if (animal.getAnimal_id() != 0) {
                        System.out.println(animal.toString());
                    } else {
                        System.out.println(ownerFirstName + " " + ownerLastName + " nie posiada zwierzęcia o takim " +
                                "imieniu!");
                    }
                    break;

                case 3:
                    System.out.println("\nWypisanie wszystkich wizyt dla właściciela: \n");

                    System.out.println("\nWszystkie wizyty dla właściciela: ");
                    for (AppointmentForOwner appointmentForOwner : ownerAppointments) {
                        System.out.println(appointmentForOwner.toString());
                    }
                    break;

                case 4:
                    System.out.println("\nWypisanie diagnoz dla wybranego zwierzęcia.: \n");

                    try {
                        System.out.print("Imię zwierzęcia: ");
                        animalName = scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    Animal animalDiagnosis = datasource.queryAnimalInformation(ownerFirstName, ownerLastName,
                            animalName);
                    if (animalDiagnosis.getAnimal_id() != 0) {

                        System.out.println("\nWszystkie diagnozy dla zwierzęcia o imieniu " + animalName + ":");
                        List<DiagnosisForAnimal> diagnosisForAnimals = datasource.queryDiagnosisForAnimal(animalName);
                        for (DiagnosisForAnimal diagnosisForAnimal : diagnosisForAnimals) {
                            System.out.println(diagnosisForAnimal.toString());
                        }

                    } else {
                        System.out.println(ownerFirstName + " " + ownerLastName + " nie posiada zwierzęcia o takim " +
                                "imieniu!");
                    }
                    break;

                case 0:
                    return;

                default:
                    break;
            }
        }
    }

    public static void functionVet(String vetFirstName, String vetLastName) {

        while (true) {
            System.out.println("\nOpcje Weterynarza: \n" +
                    "1. Wypisanie informacji szczegółowych o zwierzęciu, na podstawie imienia i nazwiska właściciela " +
                    "oraz imienia zwierzęcia.\n" +
                    "2. Wypisanie wizyt na dany dzień dla weterynarza na podstawie imienia oraz nazwiska.\n" +
                    "3. Dodanie diagnozy do zwierzęcia przez weterynarza.\n" +
                    "4. Wypisanie diagnoz dla zwierzęcia.\n" +
                    "5. Dodanie leku do recepty.\n" +
                    "0. Wyjście.\n"
            );

            System.out.print("Podaj swój wybór: ");

            int wybor = -1;
            try {
                wybor = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nPodaj prawidłowy numer!!!\n");
                scan.next();
            }

            switch (wybor) {

                case 1:

                    System.out.println("\nWypisanie informacji szczegółowych o zwierzęciu właściciela, na podstawie " +
                            "imienia i nazwiska właściciela oraz imienia zwierzęcia: \n");

                    String firstName;
                    String lastName;
                    String animalName;

                    try {
                        System.out.print("Imię: ");
                        firstName = scan.nextLine();
                        System.out.print("Nazwisko: ");
                        lastName = scan.nextLine();
                        System.out.print("Imię zwierzęcia: ");
                        animalName = scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    Animal animal = datasource.queryAnimalInformation(firstName, lastName, animalName);
                    System.out.println(animal.toString());

                    break;

                case 2:

                    System.out.println("\nWypisanie wizyt na dany dzień dla weterynarza na podstawie imienia oraz " +
                            "nazwiska: \n");

                    Date date;

                    try {
                        System.out.print("Imię: ");
                        vetFirstName = scan.nextLine();
                        System.out.print("Nazwisko: ");
                        vetLastName = scan.nextLine();
                        System.out.println("Sprawdzana data: ");
                        System.out.print("Dzień: ");
                        int day = scan.nextInt();
                        System.out.print("Miesiac: ");
                        int month = scan.nextInt();
                        System.out.print("Rok: ");
                        int year = scan.nextInt();
                        date = new Date(year - 1900, month - 1, day);
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    System.out.println("Wizyty dla weterynarza: " + vetFirstName + " " + vetLastName + " w dniu " + date + ":");
                    List<AppointmentForVeterinarian> appointmentForVeterinarians =
                            datasource.queryAppointmentForVeterinarian(vetFirstName, vetLastName, date);
                    for (AppointmentForVeterinarian appointmentForVeterinarian : appointmentForVeterinarians) {
                        System.out.println(appointmentForVeterinarian.toString());
                    }

                    if (appointmentForVeterinarians.isEmpty()) {
                        System.out.println("Brak wizyt!");
                    }

                    break;

                case 3:

                    System.out.println("\nDodanie diagnozy do zwierzęcia przez weterynarza: \n");

                    String regimen;
                    int appointID;
                    int diagnosisID;

                    try {
                        System.out.print("Podaj opis diagnozy: ");
                        regimen = scan.nextLine();
                        System.out.print("Podaj ID wizyty: ");
                        appointID = scan.nextInt();
                        System.out.print("Podaj ID diagnozy: ");
                        diagnosisID = scan.nextInt();


                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    datasource.insertAnimalDiagnosis(regimen, appointID, diagnosisID);
                    break;

                case 4:

                    System.out.println("\nWypisanie diagnoz dla zwierzęcia: \n");

                    String aniName;

                    try {
                        System.out.print("Podaj imie zwierzęcia: ");
                        aniName = scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    System.out.println("\nWszystkie diagnozy dla zwierzęcia o imieniu " + aniName + ":");
                    List<DiagnosisForAnimal> diagnosisForAnimals = datasource.queryDiagnosisForAnimal(aniName);
                    for (DiagnosisForAnimal diagnosisForAnimal : diagnosisForAnimals) {
                        System.out.println(diagnosisForAnimal.toString());
                    }

                    break;

                case 5:

                    System.out.println("\n Dodanie leku do recepty: \n");

                    String drugDes;
                    int drugID;
                    int diagID;

                    try {
                        System.out.print("Podaj opis stosowania leku: ");
                        drugDes = scan.nextLine();
                        System.out.print("Podaj ID diagnozy: ");
                        diagID = scan.nextInt();
                        System.out.print("Podaj ID leku: ");
                        drugID = scan.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    datasource.insertDrugPlan(diagID, drugDes, drugID);

                    break;

                case 0:

                    System.out.println("Wyjśćie.\n");
                    return;

                default:
                    break;

            }
        }
    }

    public static void functionAdmin() {

        while (true) {
            System.out.println("\nOpcje Administratora: \n" +
                    "1. Wypisanie wszystkich właścicieli. \n" +
                    "2. Dodanie nowego właściciela zwierzęcia do bazy danych. \n" +
                    "3. Usunięcie właściciela zwierzęcia z bazy danych. \n" +
                    "4. Wypisanie wszystkich zwierząt. \n" +
                    "5. Dodanie nowego zwierzęcia dla danego właściciela. \n" +
                    "6. Usunięcie danego zwierzęcia wybranego właściciela. \n" +
                    "7. Wypisanie wszystkich wizyt.\n" +
                    "8. Dodanie wizyty. \n" +
                    "9. Edycja wizyty. \n" +
                    "0. Wyjście.");

            System.out.print("Podaj swój wybór: ");

            int wybor = -1;
            try {
                wybor = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nPodaj prawidłowy numer!!!\n");
                scan.next();
            }

            switch (wybor) {

                case 1:
                    System.out.println("\nWypisanie wszystkich właścicieli: \n");

                    List<Owner> owners = datasource.queryOwner(2);
                    if (owners == null) {
                        System.out.println("No owners!");
                        return;
                    }

                    for (Owner owner : owners) {
                        System.out.println(owner.toString());
                    }
                    break;

                case 2:
                    System.out.println("\nDodanie nowego właściciela zwierzęcia do bazy danych:\n");

                    try {
                        System.out.print("Podaj imie: ");
                        String nameToAdd = scan.next();
                        System.out.print("Podaj nazwisko: ");
                        String surnameToAdd = scan.next();
                        System.out.println("Podaj adres: ");
                        StringBuilder address = new StringBuilder();
                        System.out.print("Ulica: ");
                        String street = scan.next();
                        address.append(street);
                        System.out.print("Miasto: ");
                        String city = scan.next();
                        address.append(" " + city);
                        System.out.print("Kod pocztowy: ");
                        String postalCode = scan.next();
                        address.append(" " + postalCode);
                        String addressToAdd = address.toString();
                        System.out.print("Podaj numer telefonu: ");
                        int number = scan.nextInt();
                        scan.nextLine();

                        datasource.insertOwner(nameToAdd, surnameToAdd, addressToAdd, number);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }
                case 3:
                    System.out.println("\nUsunięcie właściciela zwierzęcia z bazy danych:\n");

                    System.out.println("Podaj ID właściciela do usunięcia: ");
                    int idToDelete;
                    try {
                        idToDelete = scan.nextInt();
                        scan.nextLine();

                        datasource.deleteFromOwner(idToDelete);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                case 4:

                    System.out.println("\nWypisanie wszystkich zwierząt: \n");

                    List<Animal> animals = datasource.queryAnimal(2);
                    if (animals == null) {
                        System.out.println("Brak zwierząt!!!");
                        return;
                    }

                    for (Animal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    break;

                case 5:
                    System.out.println("\nDodanie nowego zwierzęcia dla danego właściciela:\n");
                    try {
                        System.out.print("Podaj imie: ");
                        String name = scan.next();
                        System.out.print("Podaj płeć: ");
                        String sex = scan.next();
                        System.out.println("Podaj date urodzenia: ");
                        System.out.print("Dzień: ");
                        int day = scan.nextInt();
                        System.out.print("Miesiac: ");
                        int month = scan.nextInt();
                        System.out.print("Rok: ");
                        int year = scan.nextInt();
                        Date date = new Date(year - 1900, month - 1, day);
                        System.out.print("Podaj gatunek: ");
                        String species = scan.next();
                        System.out.print("Podaj rasę: ");
                        String breed = scan.next();
                        System.out.print("Podaj kolor: ");
                        String color = scan.next();
                        System.out.print("Podaj rodzaj sierści: ");
                        String fur = scan.next();
                        System.out.print("Podaj id właściciela: ");
                        int ownerId = scan.nextInt();
                        scan.nextLine();

                        datasource.insertAnimal(name, sex, date, species, breed, color, fur, ownerId);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                case 6:
                    System.out.println("\nUsunięcie danego zwierzęcia wybranego właściciela:\n");

                    try {
                        System.out.println("Podaj imie zwierzęcia do usunięcia: ");
                        String name = scan.next();
                        System.out.println("Podaj id właściciela tego zwierzęcia: ");
                        int ownerId = scan.nextInt();

                        datasource.deleteFromAnimal(name, ownerId);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                case 7:

                    System.out.println("\nWypisanie wszystkich wizyt: ");

                    List<Appointment> appointments = datasource.queryAppointment(1);

                    if (appointments == null) {
                        System.out.println("No appointments!");
                        return;
                    }

                    for (Appointment appointment : appointments) {
                        System.out.println(appointment.toString());
                    }

                    break;

                case 8:

                    System.out.println("\nDodanie wizyty dla danego zwierzęcia: \n");

                    try {
                        System.out.println("Podaj date: ");
                        System.out.print("Dzień: ");
                        int dayAp = scan.nextInt();
                        System.out.print("Miesiac: ");
                        int monthAp = scan.nextInt();
                        System.out.print("Rok: ");
                        int yearAp = scan.nextInt();
                        Date dateAp = new Date(yearAp - 1900, monthAp - 1, dayAp);
                        System.out.println("Podaj godzine: ");
                        System.out.print("Godzina: ");
                        int hourAp = scan.nextInt();
                        Time timeAp = new Time(hourAp + 1, 0, 0);
                        System.out.print("ID weterynarza: ");
                        int vetID = scan.nextInt();
                        System.out.print("ID zwierzęcia: ");
                        int animalID = scan.nextInt();

                        datasource.insertAppointment(dateAp, timeAp, vetID, animalID);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                case 9:

                    System.out.println("\nEdycja wizyty: \n");

                    try {
                        System.out.println("Podaj date wizyty którą chcesz zmienić: ");
                        System.out.print("Dzień: ");
                        int oldDay = scan.nextInt();
                        System.out.print("Miesiac: ");
                        int oldMonth = scan.nextInt();
                        System.out.print("Rok: ");
                        int oldYear = scan.nextInt();
                        Date oldDate = new Date(oldYear - 1900, oldMonth - 1, oldDay);
                        System.out.println("Podaj godzine wizyty którą chcesz zmienić: ");
                        System.out.print("Godzina: ");
                        int oldHour = scan.nextInt();
                        Time oldTime = new Time(oldHour + 1, 0, 0);

                        System.out.println("Podaj nową date: ");
                        System.out.print("Dzień: ");
                        int newDay = scan.nextInt();
                        System.out.print("Miesiac: ");
                        int newMonth = scan.nextInt();
                        System.out.print("Rok: ");
                        int newYear = scan.nextInt();
                        Date newDate = new Date(newYear - 1900, newMonth - 1, newDay);
                        System.out.println("Podaj nową godzin: ");
                        System.out.print("Godzina: ");
                        int newHour = scan.nextInt();
                        Time newTime = new Time(newHour + 1, 0, 0);

                        datasource.updateAppointment(newDate, newTime, oldDate, oldTime);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                case 0:

                    System.out.println("Wyjście.\n");
                    return;

                default:

                    break;
            }
        }
    }
}
