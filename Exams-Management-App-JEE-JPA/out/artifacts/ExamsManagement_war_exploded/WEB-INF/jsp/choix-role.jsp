<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Choix du rôle</title>
</head>
<body>
<h1>Bienvenue</h1>
<p>Choisissez votre espace :</p>

<form action="authentification" method="get">
    <input type="hidden" name="role" value="etudiant" />
    <button type="submit">Étudiant</button>
</form>

<br/>

<form action="authentification" method="get">
    <input type="hidden" name="role" value="professeur" />
    <button type="submit">Professeur</button>
</form>

<br/>
<a href="index.jsp">Retour à l'accueil</a>
</body>
</html>
