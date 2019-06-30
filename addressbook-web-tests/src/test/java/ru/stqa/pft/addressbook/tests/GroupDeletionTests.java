package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {
    app.navigationHelper.gotoGroupPage();
    app.groupHelper.selectGroup();
    app.groupHelper.deleteSelectedGroups();
    app.groupHelper.returnToGroupPage();
  }

}
