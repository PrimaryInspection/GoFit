package com.company.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Lifestyle implements Serializable {
    private int id;
    private String name;

    public Lifestyle() {
    }

    public Lifestyle(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lifestyle lifestyle = (Lifestyle) o;
        return id == lifestyle.id &&
                Objects.equals(name, lifestyle.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Lifestyle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
