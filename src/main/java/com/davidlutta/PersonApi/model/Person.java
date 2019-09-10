package com.davidlutta.PersonApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable {
    private UUID id;
    @NotBlank
    private String name;
    private int age;
    @NotBlank
    private String job;

    public Person(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("age") int age,
            @JsonProperty("job") String job
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public Person(){

    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }
}
