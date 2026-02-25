<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Authentification</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/authentification.css" />
</head>
<body>
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
<div class="container">
    <div class="header">
        <h1>Authentification</h1>
        <p class="subtitle">Accédez à votre espace académique</p>
    </div>

    <p class="role-info">Rôle sélectionné : <strong><%= selectedRole %></strong></p>

    <% if (message != null) { %>
        <p class="message <%= (loginSuccess != null && loginSuccess) ? "success" : "error" %>"><strong><%= message %></strong></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/authentification" method="post" class="login-form">
        <input type="hidden" name="role" value="<%= selectedRole %>" />

        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" type="email" name="email" required />
        </div>

        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input id="password" type="password" name="password" required />
        </div>

        <button type="submit" class="submit-btn">Se connecter</button>
    </form>

    <a class="back-link" href="${pageContext.request.contextPath}/index.jsp">Retour au choix du rôle</a>

    <% if (loginSuccess != null && loginSuccess) { %>
        <p class="success-text">Test d'authentification terminé avec succès.</p>
    <% } %>
</div>
</body>
</html>
