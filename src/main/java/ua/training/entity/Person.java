package ua.training.entity;

import java.util.Objects;

public class Person {
    private Long id;
    private String name;
    private String address;
    private Integer cash;

    public Person() {
    }

    public Person(Long id, String name, String address, Integer cash) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cash = cash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(address, person.address) &&
                Objects.equals(cash, person.cash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, cash);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cash=" + cash +
                '}';
    }
}
