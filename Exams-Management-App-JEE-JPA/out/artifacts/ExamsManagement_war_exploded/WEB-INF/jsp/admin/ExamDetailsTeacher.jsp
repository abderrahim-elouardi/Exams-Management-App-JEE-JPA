<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/examDetailsTeacher.css" />
    <title>Détails Examen</title>
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Détails de l'examen</h1>
        <p class="meta"><strong>Titre :</strong> ${exam.titre}</p>
        <p class="meta"><strong>Deadline :</strong> ${exam.deadline}</p>
        <p class="meta"><strong>temps de reponse :</strong> ${exam.responseTime}</p>
        <a class="back-link" href="${pageContext.request.contextPath}/afterLoginTeacher">← Retour</a>
    </div>

    <div class="card">
        <h2>Questions</h2>
        <c:choose>
            <c:when test="${empty exam.questioner}">
                <p>Aucune question trouvée.</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach var="question" items="${exam.questioner}" varStatus="status">
                        <li>
                            <strong>Question ${status.index + 1} :</strong>
                            ${question.question}
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="card">
        <h2>Étudiants</h2>
        <c:choose>
            <c:when test="${empty exam.studentList}">
                <p>Aucun étudiant affecté.</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach var="student" items="${exam.studentList}">
                        <li>${student.firstName} ${student.lastName} - ${student.cne}</li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
