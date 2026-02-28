<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenue - ExamPrep</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="container">
    <div class="header-icon">📚</div>

    <h1>Bienvenue sur ExamPrep</h1>

    <div class="subtitle">Votre compagnon de préparation aux examens</div>

    <p class="description">
        Découvrez une application complète conçue pour vous aider à préparer, organiser et gérer vos examens avec efficacité. Que vous soyez étudiant ou professionnel, ExamPrep vous accompagne à chaque étape de votre parcours académique.
    </p>

    <div class="features">
        <div class="feature">
            <div class="feature-icon">✓</div>
            <div class="feature-title">Préparation Intelligente</div>
            <div class="feature-text">Des outils et ressources adaptés à votre niveau</div>
        </div>
        <div class="feature">
            <div class="feature-icon">📋</div>
            <div class="feature-title">Organisation Facile</div>
            <div class="feature-text">Gérez vos examens et planifiez vos études</div>
        </div>
        <div class="feature">
            <div class="feature-icon">📊</div>
            <div class="feature-title">Suivi de Progrès</div>
            <div class="feature-text">Visualisez votre évolution en temps réel</div>
        </div>
        <div class="feature">
            <div class="feature-icon">🎯</div>
            <div class="feature-title">Objectifs Clairs</div>
            <div class="feature-text">Atteindrez vos meilleures performances</div>
        </div>
    </div>

    <button class="btn"><a href="${pageContext.request.contextPath}/professeur-etudiant-choix-role.jsp">Commencer Maintenant</a></button>
</div>
</body>
</html>
