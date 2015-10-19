package com.csi.jf.common;

import java.sql.Timestamp;

public class Student {
    private int id;
    private String name;
    private int age;
    private Timestamp birthday;
    /**
     * @return Returns the id.
     */
    public int getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the age.
     */
    public int getAge() {
        return age;
    }
    /**
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * @return Returns the birthday.
     */
    public Timestamp getBirthday() {
        return birthday;
    }
    /**
     * @param birthday The birthday to set.
     */
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }
    

}
