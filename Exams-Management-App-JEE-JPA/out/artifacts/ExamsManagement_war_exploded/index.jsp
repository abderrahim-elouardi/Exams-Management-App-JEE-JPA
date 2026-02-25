<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - Gestion Scolaire</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Portail Académique</h1>
        <p class="subtitle">Sélectionnez votre espace d'accès</p>
    </div>

    <div class="role-section">
        <form action="jsp/authentification" method="get" class="role-form">
            <input type="hidden" name="role" value="etudiant" />
            <button type="submit" class="role-button etudiant-btn">Espace Étudiant</button>
        </form>

        <form action="jsp/authentification" method="get" class="role-form">
            <input type="hidden" name="role" value="professeur" />
            <button type="submit" class="role-button professeur-btn">Espace Professeur</button>
        </form>
    </div>

    <p class="footer">Système de gestion des examens • Accès sécurisé</p>
</div>
</body>
</html>