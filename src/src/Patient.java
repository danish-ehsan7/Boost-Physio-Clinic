
public class Patient {
    private static int idCounter = 1; 
    private int id;
    private String name;
    private String address;
    private String phone;

    // Constructor to initialize a patient with name, address, and phone
    public Patient(String name, String address, String phone) {
        this.id = idCounter++; 
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getters and setters for the patient attributes
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    // Override toString method for easy display of patient information
    @Override
    public String toString() {
        // return "Patient [ID=" + id + ", Name=" + name + ", Address=" + address + ", Phone=" + phone + "]";
        return name;
    }
}
