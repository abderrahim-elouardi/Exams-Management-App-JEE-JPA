<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Choix préparation examen</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/authentification.css" />
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Préparer un Examen</h1>
        <p class="subtitle">Choisissez la méthode de préparation</p>
    </div>

    <div class="login-form">
        <a class="submit-btn" style="display:block;text-align:center;text-decoration:none;margin-bottom:12px;" href="${pageContext.request.contextPath}/preparerExamManuelle.jsp?mode=manual">Configuration manuelle</a>
        <a class="submit-btn" style="display:block;text-align:center;text-decoration:none;" href="${pageContext.request.contextPath}/preparerExam.jsp?mode=upload">Upload fichier</a>
    </div>

    <a class="back-link" href="${pageContext.request.contextPath}/index.jsp">Retour</a>
</div>
</body>
</html>
