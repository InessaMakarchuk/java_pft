package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String emailAddress;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String emailAddress2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String emailAddress3;

  @Transient
  private String allEmailAddresses;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))

  private Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public ContactData withEmailAddress2(String emailAddress2) {
    this.emailAddress2 = emailAddress2;
    return this;
  }

  public ContactData withEmailAddress3(String emailAddress3) {
    this.emailAddress3 = emailAddress3;
    return this;
  }

  public ContactData withAllEmailAddresses(String allEmails) {
    this.allEmailAddresses = allEmails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }

  public String getFirstName() { return firstName; }

  public String getLastName() { return lastName; }

  public String getAddress() { return address; }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() { return allPhones; }

  public String getEmailAddress() { return emailAddress; }

  public String getEmailAddress2() { return emailAddress2; }

  public String getEmailAddress3() { return emailAddress3; }

  public String getAllEmailAddresses() { return allEmailAddresses; }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public File getPhoto() {
    if (photo == null) {
      return null;
    } else {
      return new File(photo);
    }
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(address, that.address) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(workPhone, that.workPhone) &&
            Objects.equals(emailAddress, that.emailAddress) &&
            Objects.equals(emailAddress2, that.emailAddress2) &&
            Objects.equals(emailAddress3, that.emailAddress3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address, homePhone, mobilePhone, workPhone, emailAddress, emailAddress2, emailAddress3);
  }

}
