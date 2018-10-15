package me.regstudio.pd_app.DataStructures;

import java.util.Arrays;
import java.util.List;

public class PatientFile {

    /* A class to help structure the patient information file.

     */

    private String user_id;

    private String first_name;
    private String last_name;
    private String sex;
    private int age;
    private int height;
    private int weight;
    private String condition;

    public PatientFile() {}

    public PatientFile(String first_name, String last_name, String sex, int age, int height, int weight, String condition) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.condition = condition;
    }

    public void addUserId(String user_id) { this.user_id = user_id;}
    public String getUserId() { return this.user_id; }

    public void addFirstName(String first_name) { this.first_name = first_name; }
    public String getFirstName() { return this.first_name; }

    public void addLastName(String last_name) { this.last_name = last_name; }
    public String getLastName() { return this.last_name; }

    public void addSex(String sex) { this.sex = sex; }
    public String getSex() { return this.sex; }

    public void addAge(int age) { this.age = age; }
    public int getAge() { return this.age; }

    public void addHeight(int height) { this.height = height; }
    public int getHeight() { return this.height; }

    public void addWeight(int weight) { this.weight = weight; }
    public int getWeight() { return this.weight; }

    public void addCondition(String condition) { this.condition = condition; }
    public String getCondition() { return this.condition; }

}
