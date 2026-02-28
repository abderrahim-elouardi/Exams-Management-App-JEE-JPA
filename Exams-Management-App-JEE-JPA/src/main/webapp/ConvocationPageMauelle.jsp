<%@ page import="java.util.List" %>
<%@ page import="com.fsdm.examsmanagement.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Student> students = (List<Student>) request.getAttribute("students");
  Integer currentPage = (Integer) request.getAttribute("currentPage");
  Integer totalPages = (Integer) request.getAttribute("totalPages");
  Long totalStudents = (Long) request.getAttribute("totalStudents");
  Integer selectedCount = (Integer) request.getAttribute("selectedCount");

  if (students == null) {
    students = java.util.Collections.emptyList();
  }
  if (currentPage == null) {
    currentPage = 1;
  }
  if (totalPages == null) {
    totalPages = 1;
  }
  if (totalStudents == null) {
    totalStudents = 0L;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ajout manuel des étudiants</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/convocationPageMauelle.css" />
</head>
<body>
<div class="container">
  <h1>Sélection manuelle des étudiants</h1>
  <p class="meta">Total des étudiants: <strong><%= totalStudents %></strong></p>

  <% if (selectedCount != null) { %>
  <div class="message"><%= selectedCount %> étudiant(s) coché(s).</div>
  <% } %>

  <form method="post" action="${pageContext.request.contextPath}/preparerExamController">
    <table>
      <thead>
      <tr>
        <th>Cocher</th>
        <th>ID</th>
        <th>Nom & Prénom</th>
      </tr>
      </thead>
      <tbody>
      <% if (students.isEmpty()) { %>
      <tr>
        <td colspan="3">Aucun étudiant trouvé.</td>
      </tr>
      <% } else { %>
      <% for (Student student : students) { %>
      <tr>
        <td>
          <input type="checkbox" name="selectedStudentIds" value="<%= student.getId() %>">
        </td>
        <td><%= student.getId() %></td>
        <td><%= student.getLastName() %> <%= student.getFirstName() %></td>
      </tr>
      <% } %>
      <% } %>
      </tbody>
    </table>

    <div class="submit-zone">
      <button type="submit">Créer l'examen avec les étudiants cochés</button>
    </div>
  </form>

  <div class="pagination">
    <%
      boolean hasPrevious = currentPage > 1;
      boolean hasNext = currentPage < totalPages;
    %>
    <a class="<%= hasPrevious ? "" : "disabled" %>"
       href="${pageContext.request.contextPath}/convocationPageMauelleController?page=<%= currentPage - 1 %>">← Précédent</a>

    <span>Page <strong><%= currentPage %></strong> / <strong><%= totalPages %></strong></span>

    <a class="<%= hasNext ? "" : "disabled" %>"
       href="${pageContext.request.contextPath}/convocationPageMauelleController?page=<%= currentPage + 1 %>">Suivant →</a>
  </div>
</div>
</body>
</html>
