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
                    for(Owner owner: owners) {
                        if(owner.getFirst_name().equals(ownerName) && owner.getLast_name().equals(ownerSurname) && (owner.getPhone_number() == phoneNumber)) {
                            isCorrect = true;
                        }
                    }

                    if(isCorrect) {
                        Main.functionOwner(ownerName,ownerSurname);
                    } else {
                        System.out.println("Podano nieprawidłowe dane!");
                    }

                     break;
                case 2:
                    System.out.println("Weterynarz\n");
                    break;
                case 3:
                    System.out.println("Administrator\n");
                    Main.functionAdmin();
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

        while (true)
        {
            System.out.println("\nOpcje Właściciela: \n" +
                    "1. Wypisanie wszsytkich zwierząt właściciela. \n" +
                    "2. Wypisanie informacji szczegółowych o zwierzęciu właściciela. \n" +
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

                    List<Animal> animalList = datasource.queryAnimalsByOwner(ownerFirstName, ownerLastName, 3);
                    if(animalList == null){
                        System.out.println("Nie udało się odnaleźć zwierząt dla właściciela: " + ownerFirstName + " " + ownerLastName);
                        return;
                    }

                    for(Animal animal : animalList){
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

                    Animal animal = datasource.queryAnimalInformation(ownerFirstName,ownerLastName,animalName);
                    System.out.println(animal.toString());

                    break;

                case 0:
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
                    "4. Dodanie nowego zwierzęcia dla danego właściciela. \n" +
                    "5. Usunięcie danego zwierzęcia wybranego właściciela. \n" +
                    "6. Wypisanie wszystkich zwierząt właściciela, na podstawie imienia oraz nazwiska właściciela. \n" +
                    "7. Wypisanie informacji szczegółowych o zwierzęciu właściciela, na podstawie imienia i nazwiska właściciela oraz i imienia zwierzęcia. \n" +
                    "8. Dodanie wizyty dla danego zwierzęcia. \n" +
                    "9. Edycja wizyty. \n" +
                    "10. Wypisanie wszystkich wizyt dla danego właściciela, na podstawie imienia oraz nazwiska właściciela. \n" +
                    "11. Wypisanie diagnoz dla zwierzęcia. \n" +
                    "12. Wypisanie wizyt na dany dzień dla weterynarza na podstawie imienia oraz nazwiska. \n" +
                    "13. Dodanie diagnozy do zwierzęcia przez weterynarza. \n" +
                    "14. Dodanie leku do recepty. \n" +
                    "0. Wyjście.");

            System.out.print("Podaj swój wybór: ");

            int wybor = -1;
            try {
                wybor = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e){
                System.out.println("\nPodaj prawidłowy numer!!!\n");
                scan.next();
            }

            switch (wybor) {

                case 1:
                    System.out.println("\nWypisanie wszystkich właścicieli: \n");

                    List<Owner> owners = datasource.queryOwner(3);
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

                        datasource.insertOwner(nameToAdd,surnameToAdd,addressToAdd,number);
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

                case 5:
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

                case 6:
                    System.out.println("\nWypisywanie zwierząt danego właściciela:\n");

                    String ownerFirstName;
                    String ownerLastName;

                    try {
                        System.out.print("Imię: ");
                        ownerFirstName = scan.nextLine();
                        System.out.print("Nazwisko: ");
                        ownerLastName = scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    List<Animal> animalList = datasource.queryAnimalsByOwner(ownerFirstName, ownerLastName, 3);
                    if(animalList == null){
                        System.out.println("Nie udało się odnaleźć zwierząt dla tego właściciela");
                        return;
                    }

                    for(Animal animal : animalList){
                        System.out.println(animal.getName() + "\t" + animal.getSex() + "\t" + animal.getBirth_date() + "\t" +
                        animal.getSpecies());
                    }

                    break;

                case 7:
                    System.out.println("\nWypisanie informacji szczegółowych o zwierzęciu właściciela, na podstawie imienia i nazwiska właściciela oraz imienia zwierzęcia: \n");

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

                    Animal animal = datasource.queryAnimalInformation(firstName,lastName,animalName);
                    System.out.println(animal.toString());

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
                        Time timeAp = new Time(hourAp + 1, 0 , 0 );
                        System.out.print("ID weterynarza: ");
                        int vetID = scan.nextInt();
                        System.out.print("ID zwierzęcia: ");
                        int animalID = scan.nextInt();

                        datasource.insertAppointment(dateAp,timeAp,vetID,animalID);
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
                        Time oldTime = new Time(oldHour + 1, 0 , 0 );

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
                        Time newTime = new Time(newHour + 1, 0 , 0 );

                        datasource.updateAppointment(newDate,newTime,oldDate,oldTime);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }
                case 10:
                    System.out.println("\nWypisanie wszystkich wizyt dla danego właściciela, na podstawie imienia oraz nazwiska właściciela: \n");

                    String surname;

                    try {
                        System.out.print("Podaj nawzisko: ");
                        surname = scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nPodano nieprawidłowe dane!");
                        break;
                    }

                    System.out.println("\nWszystkie wizyty dla właściciela o nazwisku " + surname + ":");
                    List<AppointmentForOwner> appointmentForOwnerList = datasource.queryAppointmentForOwner(surname);
                    for(AppointmentForOwner appointmentForOwner : appointmentForOwnerList) {
                        System.out.println(appointmentForOwner.toString());
                    }
                    break;

                case 11:
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
                    for(DiagnosisForAnimal diagnosisForAnimal : diagnosisForAnimals) {
                        System.out.println(diagnosisForAnimal.toString());
                    }

                    break;

                case 12:
                    System.out.println("\nWypisanie wizyt na dany dzień dla weterynarza na podstawie imienia oraz nazwiska: \n");

                    String vetFirstName;
                    String vetLastName;
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
                    List<AppointmentForVeterinarian> appointmentForVeterinarians = datasource.queryAppointmentForVeterinarian(vetFirstName,vetLastName,date);
                    for(AppointmentForVeterinarian appointmentForVeterinarian : appointmentForVeterinarians) {
                        System.out.println(appointmentForVeterinarian.toString());
                    }

                    break;

                case 13:
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

                case 14:
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
}
