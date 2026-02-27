<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenue - ExamPrep</title>
<<<<<<< HEAD
<%--    <link rel="stylesheet" href="css/index.css">--%>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }



        .container {
            background: white;
            border-radius: 16px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
            max-width: 600px;
            width: 100%;
            padding: 60px 40px;
            text-align: center;
        }

        .header-icon {
            width: 80px;
            height: 80px;
            margin: 0 auto 30px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 40px;
        }

        h1 {
            color: #2c3e50;
            font-size: 36px;
            margin-bottom: 16px;
            font-weight: 700;
        }

        .subtitle {
            color: #667eea;
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 24px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .description {
            color: #555;
            font-size: 16px;
            line-height: 1.8;
            margin-bottom: 40px;
            text-align: justify;
        }

        .features {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-bottom: 40px;
            text-align: left;
        }

        .feature {
            padding: 20px;
            background: #f8f9fa;
            border-radius: 12px;
            border-left: 4px solid #667eea;
        }

        .feature-icon {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .feature-title {
            color: #2c3e50;
            font-weight: 600;
            font-size: 14px;
            margin-bottom: 5px;
        }

        .feature-text {
            color: #777;
            font-size: 13px;
            line-height: 1.6;
        }

        .btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 16px 48px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 8px;
            cursor: pointer;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 1px;
            box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 15px 35px rgba(102, 126, 234, 0.6);
        }

        .btn:active {
            transform: translateY(0);
        }

        @media (max-width: 600px) {
            .container {
                padding: 40px 24px;
            }

            h1 {
                font-size: 28px;
            }

            .features {
                grid-template-columns: 1fr;
            }

            .subtitle {
                font-size: 14px;
            }

            .description {
                font-size: 15px;
                text-align: center;
            }

            .feature {
                text-align: center;
                border-left: none;
                border-top: 4px solid #667eea;
            }
        }
    </style>
=======
    <link rel="stylesheet" href="css/index.css">
>>>>>>> d7e05ee (add upload file and interface chose prepared exam)
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

    <button class="btn"><a href="professeur-etudiant-choix-role.jsp">Commencer Maintenant</a></button>
</div>
</body>
</html>
