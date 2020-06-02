package model;

public class Owner {

    int owner_id;
    String first_name;
    String last_name;
    String address;
    int phone_number;

    public int getOwner_id() { return owner_id; }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString()
    {
        return "Właściciel" +
                "id = " + owner_id +
                ", imie = '" + first_name + '\'' +
                ", naziwsko = '" + last_name + '\'' +
                ", adres = '" + address + '\'' +
                ", numer telefonu = " + phone_number;
    }
}
