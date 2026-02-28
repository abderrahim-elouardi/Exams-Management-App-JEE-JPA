<%@ page import="com.fsdm.examsmanagement.model.User" %>
<%@ page import="com.fsdm.examsmanagement.model.Student" %>
<%@ page import="com.fsdm.examsmanagement.model.Administrator" %><%--
  Created by IntelliJ IDEA.
  User: EL OUARDI
  Date: 23/02/2026
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenue</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Helvetica Neue', sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            color: #2c3e50;
            min-height: 100vh;
        }

        /* Navbar */
        nav {
            background: linear-gradient(90deg, #1a1a2e 0%, #16213e 100%);
            color: white;
            padding: 1rem 2rem;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            position: sticky;
            top: 0;
            z-index: 100;
        }

        .navbar-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            gap: 1rem;
        }

        .navbar-brand {
            font-size: 1.6rem;
            font-weight: 700;
            letter-spacing: -0.5px;
            text-transform: uppercase;
            color: #fff;
        }

        .navbar-right {
            display: flex;
            align-items: center;
            gap: 2rem;
        }

        .welcome-message {
            font-size: 1rem;
            font-weight: 500;
            color: #e0e0e0;
        }

        .logout-btn {
            background-color: #ff5252;
            color: white;
            border: none;
            padding: 0.7rem 1.5rem;
            border-radius: 6px;
            cursor: pointer;
            font-size: 0.95rem;
            font-weight: 600;
            transition: all 0.3s ease;
            box-shadow: 0 2px 8px rgba(255, 82, 82, 0.3);
        }

        .logout-btn:hover {
            background-color: #ff3838;
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(255, 82, 82, 0.4);
        }

        /* Main Content */
        main {
            max-width: 1000px;
            margin: 3rem auto;
            padding: 0 2rem;
        }

        .section-title {
            font-size: 2.2rem;
            margin-bottom: 2.5rem;
            color: #1a1a2e;
            font-weight: 700;
            letter-spacing: -0.5px;
        }

        /* Table Styles */
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
            border-radius: 8px;
            overflow: hidden;
        }

        thead {
            background: linear-gradient(90deg, #2c3e50 0%, #34495e 100%);
            color: white;
        }

        th {
            padding: 1.2rem 1.5rem;
            text-align: left;
            font-weight: 600;
            letter-spacing: 0.4px;
            font-size: 0.95rem;
            text-transform: uppercase;
        }

        td {
            padding: 1.2rem 1.5rem;
            border-bottom: 1px solid #e8e8e8;
            font-size: 0.95rem;
        }

        tbody tr {
            transition: all 0.3s ease;
        }

        tbody tr:hover {
            background-color: #f8f9fa;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05);
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .navbar-container {
                justify-content: space-between;
            }

            .navbar-right {
                gap: 1.5rem;
                flex-direction: column;
                width: 100%;
                align-items: stretch;
            }

            .welcome-message {
                text-align: center;
            }

            .logout-btn {
                width: 100%;
                text-align: center;
            }

            .section-title {
                font-size: 1.8rem;
                margin-bottom: 1.8rem;
            }

            th, td {
                padding: 0.9rem 1rem;
                font-size: 0.85rem;
            }

            th {
                font-size: 0.8rem;
            }
        }

        @media (max-width: 480px) {
            nav {
                padding: 0.8rem 1rem;
            }

            .navbar-brand {
                font-size: 1.3rem;
            }

            .navbar-right {
                gap: 1rem;
            }

            .welcome-message {
                font-size: 0.9rem;
            }

            main {
                margin: 2rem auto;
                padding: 0 1rem;
            }

            .section-title {
                font-size: 1.5rem;
                margin-bottom: 1.5rem;
            }

            th, td {
                padding: 0.8rem 0.75rem;
                font-size: 0.8rem;
            }

            th {
                font-size: 0.75rem;
            }
        }
    </style>
</head>
<body>
<!-- Navigation Bar -->
<nav>
    <div class="navbar-container">
        <div class="navbar-brand">MyApp</div>
        <div class="navbar-right">
            <div class="welcome-message">Bienvenue,Professeur <%=((Administrator)session.getAttribute("admin")).getFirstName()+((Administrator)session.getAttribute("admin")).getFirstName()%></div>
            <a href="${pageContext.request.contextPath}/choixPreparationExam.jsp" class="logout-btn">Preparer un Examen</a>
            <form action="deconnection" method="POST">
                <button class="logout-btn">Déconnexion</button>
            </form>
        </div>
    </div>
</nav>

<!-- Main Content -->
<main>
    <h1 class="section-title">Liste des Examens</h1>

    <table>
        <thead>
        <tr>
            <th>Titre</th>
            <th>DeadLine</th>
            <th>Administrateur</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="exam" items="${sessionScope.exams}">
            <tr>
                <td>${exam.titre}</td>
                <td>${exam.deadline.toString()}</td>
                <td>${exam.admin.firstName} ${exam.admin.lastName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/examDetails?idExam=${exam.idExam}">Voir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>
