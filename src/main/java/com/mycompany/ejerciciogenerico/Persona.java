package com.mycompany.ejerciciogenerico;

/**
 *
 * @author Andres Felipe Franco Sepulveda
 */
public class Persona {
    
    private Integer id;
    private String name;
    private Integer age;
    private String eps;
    private String birthday;

    public Persona(Integer age, String name, String eps, String birthday) {
        this.name = name;
        this.age = age;
        this.eps = eps;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEps() {
        return eps;
    }

    public String getBirthday() {
        return birthday;
    }
    
    
    
}
