package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType()); //List<GroupData>.class
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    Groups beforedb = new Groups(before.stream()
            .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())
                    .withHeader(g.getHeader().replace("\r\n", "\n"))
                    .withFooter(g.getFooter().replace("\r\n", "\n")))
            .collect(Collectors.toSet()));
    app.group().create(group);
    Groups after = app.db().groups();
    Groups afterdb = new Groups(after.stream()
            .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())
                    .withHeader(g.getHeader().replace("\r\n", "\n"))
                    .withFooter(g.getFooter().replace("\r\n", "\n")))
            .collect(Collectors.toSet()));
    assertThat(app.group().count(), equalTo(before.size() + 1));
    assertThat(afterdb.stream()
                    .sorted(Comparator.comparing(GroupData :: getId))
                    .collect(Collectors.toList()),
            equalTo(beforedb.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())).stream()
                    .sorted(Comparator.comparing(GroupData::getId))
                    .collect(Collectors.toList())));
  }

  @Test(enabled = false)
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("TestGroup2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
