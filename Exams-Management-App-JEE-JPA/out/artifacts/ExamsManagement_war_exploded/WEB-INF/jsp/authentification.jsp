<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Authentification</title>
</head>
<body>
<h1>Page d'authentification</h1>

<%
    String selectedRole = (String) request.getAttribute("selectedRole");
    if (selectedRole == null || selectedRole.trim().isEmpty()) {
        selectedRole = request.getParameter("role");
    }
    if (selectedRole == null || selectedRole.trim().isEmpty()) {
        selectedRole = "etudiant";
    }

    String message = (String) request.getAttribute("message");
    Boolean loginSuccess = (Boolean) request.getAttribute("loginSuccess");
%>

<p>Rôle sélectionné : <strong><%= selectedRole %></strong></p>

<% if (message != null) { %>
    <p><strong><%= message %></strong></p>
<% } %>

<form action="jsp/authentification" method="post">
    <input type="hidden" name="role" value="<%= selectedRole %>" />

    <label>Email :</label>
    <input type="email" name="email" required />
    <br/><br/>

    <label>Mot de passe :</label>
    <input type="password" name="password" required />
    <br/><br/>

    <button type="submit">Se connecter</button>
</form>

<br/>
<a href="choix-role.jsp">Retour au choix du rôle</a>

<% if (loginSuccess != null && loginSuccess) { %>
    <p>Test d'authentification terminé avec succès.</p>
<% } %>
</body>
</html>
