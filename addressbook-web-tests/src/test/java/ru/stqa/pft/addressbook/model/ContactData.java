package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstName;
  private final String lastName;
  private final String homePhone;
  private final String mobilePhone;
  private final String workPhone;
  private final String emailAddress;
  private String group;

  public void setId(int id) {
    this.id = id;
  }

    public int getId() {
    return id;
  }

  public ContactData(int id, String firstName, String lastName, String homePhone, String mobilePhone, String workPhone, String emailAddress, String group) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.workPhone = workPhone;
    this.emailAddress = emailAddress;
    this.group = group;
  }

  public ContactData(String firstName, String lastName, String homePhone, String mobilePhone, String workPhone, String emailAddress, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.workPhone = workPhone;
    this.emailAddress = emailAddress;
    this.group = group;
  }

  public String getFirstName() { return firstName; }

  public String getLastName() { return lastName; }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

}
