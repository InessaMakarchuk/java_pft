package ru.stqa.pft.rest;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testSkipIfNotFixedIssuesExist() throws IOException {
    Set<Issue> issues = getIssues();
    for (Issue issue : issues) {
      System.out.println("Issue id: " + issue.getId());
      System.out.println("Issue status: "+ issue.getState());
      skipIfNotFixed(issue.getId());
    }
  }
}
