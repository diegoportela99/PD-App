package me.regstudio.pd_app.DataStructures;

import android.widget.EditText;

import java.util.List;

public class PatientFile {

    /* A class to help structure the patient information file.

     */

    public String first_name;
    public String last_name;
    public String sex;
    public int age;
    public double height;
    public double weight;
    public String condition;

    public PatientFile() {}

    public PatientFile(List<EditText> editTexts) {

    }

    public PatientFile(String first_name, String last_name, String sex, int age, double height, double weight, String condition) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.condition = condition;
    }

}
