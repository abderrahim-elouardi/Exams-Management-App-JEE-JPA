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

  <form id="manualSelectionForm" method="post" action="${pageContext.request.contextPath}/convocationPageMauelleController">
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
          <input type="checkbox" class="student-checkbox" value="<%= student.getId() %>">
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

    <div id="selected-students-hidden-inputs"></div>
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

<script>
  (function () {
    const STORAGE_KEY = 'manualConvocationSelectedStudentIds';
    const form = document.getElementById('manualSelectionForm');
    const hiddenInputsContainer = document.getElementById('selected-students-hidden-inputs');
    const checkboxes = Array.from(document.querySelectorAll('.student-checkbox'));

    function getStoredSelection() {
      try {
        const rawValue = sessionStorage.getItem(STORAGE_KEY);
        const parsed = rawValue ? JSON.parse(rawValue) : [];
        return new Set(Array.isArray(parsed) ? parsed.map(String) : []);
      } catch (error) {
        return new Set();
      }
    }

    function saveSelection(selectedSet) {
      sessionStorage.setItem(STORAGE_KEY, JSON.stringify(Array.from(selectedSet)));
    }

    function applySelectionOnCurrentPage(selectedSet) {
      checkboxes.forEach(function (checkbox) {
        checkbox.checked = selectedSet.has(String(checkbox.value));
      });
    }

    function updateSelectionFromCurrentPage(selectedSet) {
      checkboxes.forEach(function (checkbox) {
        const studentId = String(checkbox.value);
        if (checkbox.checked) {
          selectedSet.add(studentId);
        } else {
          selectedSet.delete(studentId);
        }
      });
    }

    function syncHiddenInputs(selectedSet) {
      hiddenInputsContainer.innerHTML = '';
      Array.from(selectedSet).forEach(function (studentId) {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'selectedStudentIds';
        input.value = studentId;
        hiddenInputsContainer.appendChild(input);
      });
    }

    const selectedSet = getStoredSelection();
    applySelectionOnCurrentPage(selectedSet);

    checkboxes.forEach(function (checkbox) {
      checkbox.addEventListener('change', function () {
        updateSelectionFromCurrentPage(selectedSet);
        saveSelection(selectedSet);
      });
    });

    form.addEventListener('submit', function () {
      updateSelectionFromCurrentPage(selectedSet);
      saveSelection(selectedSet);
      syncHiddenInputs(selectedSet);
      sessionStorage.removeItem(STORAGE_KEY);
    });
  })();
</script>
</body>
</html>
