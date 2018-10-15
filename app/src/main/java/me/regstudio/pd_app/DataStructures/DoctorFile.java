package me.regstudio.pd_app.DataStructures;

public class DoctorFile {
    /* A class to help structure the doctor info file

     */

    private String user_id;
    private String first_name;
    private String last_name;
    private String specialty;

    public DoctorFile() {}

    public DoctorFile(String first_name, String last_name, String specialty) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialty = specialty;
    }

    public void addUserId(String user_id) { this.user_id = user_id; }
    public String getUserId() { return this.user_id; }

    public void addFirstName(String first_name) { this.first_name = first_name; }
    public String getFirstName() { return this.first_name; }

    public void addLastName(String last_name) { this.last_name = last_name; }
    public String getLastName() { return this.last_name; }

    public void addSpecialty(String specialty) { this.specialty = specialty; }
    public String getSpecialty() { return this.specialty; }

}
