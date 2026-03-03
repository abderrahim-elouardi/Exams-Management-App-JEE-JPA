<%--
  Created by IntelliJ IDEA.
  User: EL OUARDI
  Date: 24/02/2026
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Préparation d'un Examen</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/preparerExam.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>📚 Préparation d'un Examen</h1>
        <p class="description">Créez et préparez un nouvel examen en quelques étapes simples. Téléchargez vos questions et fixez votre deadline pour commencer votre préparation.</p>
    </div>

    <form action="${pageContext.request.contextPath}/preparerExamController" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="exam-title">Titre de l'examen</label>
            <input type="text" id="exam-title" name="exam-title" placeholder="Ex: Mathématiques - Chapitre 5" required>
        </div>

        <div class="form-group">
            <label for="exam-deadline">Date limite</label>
            <input type="date" id="exam-deadline" name="exam-deadline" required>
        </div>

        <div class="form-group">
            <label for="exam-file">Télécharger le fichier de questions</label>
            <input type="file" id="exam-file" name="exam-file" accept=".txt" required>
        </div>

        <div class="form-group">
            <label for="exam-duration">Temps de l'examen (minutes)</label>
            <input type="number" id="exam-duration" name="exam-duration" min="1" placeholder="Ex: 90" required>
        </div>

        <div class="actions">
            <button type="submit" formaction="${pageContext.request.contextPath}/ConvocationPage.jsp?mode=upload" formmethod="get">
                Upload IDs des étudiants
            </button>
            <button type="submit" formaction="${pageContext.request.contextPath}/convocationPageMauelleController" formmethod="post">
                Ajouter des étudiants manuellement
            </button>
        </div>
    </form>
</div>
</body>
</html>
