<%--
  Created by IntelliJ IDEA.
  User: EL OUARDI
  Date: 25/02/2026
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - Gestion Scolaire</title>
    <link rel="stylesheet" href="css/professeur-etudiant-choix-role-style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Portail Académique</h1>
        <p class="subtitle">Sélectionnez votre espace d'accès</p>
    </div>

    <div class="role-section">
        <form action="jsp/authentification" method="get" class="role-form">
            <input type="hidden" name="role" value="student" />
            <button type="submit" class="role-button etudiant-btn">Espace Étudiant</button>
        </form>

        <form action="jsp/authentification" method="get" class="role-form">
            <input type="hidden" name="role" value="admin" />
            <button type="submit" class="role-button professeur-btn">Espace Professeur</button>
        </form>
    </div>

    <p class="footer">Système de gestion des examens • Accès sécurisé</p>
</div>
</body>
</html>