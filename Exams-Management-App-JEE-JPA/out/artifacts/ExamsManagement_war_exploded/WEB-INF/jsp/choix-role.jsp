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

<form action="${pageContext.request.contextPath}/authentification" method="get">
    <input type="hidden" name="role" value="student" />
    <button type="submit">Étudiant</button>
</form>

<br/>

<form action="${pageContext.request.contextPath}/authentification" method="get">
    <input type="hidden" name="role" value="admin" />
    <button type="submit">Professeur</button>
</form>

<br/>
<a href="${pageContext.request.contextPath}/index.jsp">Retour à l'accueil</a>
</body>
</html>
