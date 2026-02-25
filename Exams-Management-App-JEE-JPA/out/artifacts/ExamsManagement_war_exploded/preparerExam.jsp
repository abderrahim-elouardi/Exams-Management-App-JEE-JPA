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
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #e8f4f8 0%, #f0f8f4 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .container {
            background: white;
            border-radius: 16px;
            box-shadow: 0 10px 40px rgba(0, 102, 102, 0.1);
            max-width: 500px;
            width: 100%;
            padding: 40px;
        }

        .header {
            text-align: center;
            margin-bottom: 40px;
        }

        h1 {
            color: #006666;
            font-size: 28px;
            margin-bottom: 12px;
            font-weight: 600;
        }

        .description {
            color: #555;
            font-size: 14px;
            line-height: 1.6;
        }

        .form-group {
            margin-bottom: 24px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
            font-size: 14px;
        }

        input[type="text"],
        input[type="date"],
        input[type="file"] {
            width: 100%;
            padding: 12px 14px;
            border: 2px solid #d0e8e8;
            border-radius: 8px;
            font-size: 14px;
            font-family: inherit;
            transition: all 0.3s ease;
            color: #333;
        }

        input[type="text"]:focus,
        input[type="date"]:focus,
        input[type="file"]:focus {
            outline: none;
            border-color: #00a3a3;
            box-shadow: 0 0 0 3px rgba(0, 163, 163, 0.1);
        }

        input[type="text"]::placeholder {
            color: #999;
        }

        input[type="file"]::file-selector-button {
            background-color: #00a3a3;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
            margin-right: 10px;
            transition: background-color 0.3s ease;
        }

        input[type="file"]::file-selector-button:hover {
            background-color: #008080;
        }

        button {
            width: 100%;
            padding: 14px;
            background-color: #00b359;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 10px;
        }

        button:hover {
            background-color: #009147;
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(0, 179, 89, 0.3);
        }

        button:active {
            transform: translateY(0);
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .container {
                padding: 30px 20px;
            }

            h1 {
                font-size: 24px;
            }

            .description {
                font-size: 13px;
            }

            input[type="text"],
            input[type="date"],
            input[type="file"] {
                padding: 10px 12px;
                font-size: 16px;
            }

            button {
                padding: 12px;
                font-size: 15px;
            }
        }
    </style>
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

        <button type="submit">Créer l'Examen</button>
    </form>
</div>
</body>
</html>
