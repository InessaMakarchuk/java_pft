package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lasname;
  private final String homephone;
  private final String mobilephone;
  private final String workphone;
  private final String emailaddress;

  public ContactData(String firstname, String lasname, String homephone, String mobilephone, String workphone, String emailaddress) {
    this.firstname = firstname;
    this.lasname = lasname;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.workphone = workphone;
    this.emailaddress = emailaddress;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLasname() {
    return lasname;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getEmailaddress() {
    return emailaddress;
  }
}
