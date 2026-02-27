<%--
  Created by IntelliJ IDEA.
  User: EL OUARDI
  Date: 27/02/2026
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Convocation des Étudiants - Administrateur</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            background: linear-gradient(135deg, #f0f9ff 0%, #f3e8ff 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .container {
            background: white;
            border-radius: 12px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
            padding: 60px 40px;
            max-width: 500px;
            width: 100%;
        }

        h1 {
            font-size: 28px;
            color: #1a1a1a;
            margin-bottom: 12px;
            text-align: center;
            font-weight: 600;
        }

        .description {
            color: #666;
            font-size: 15px;
            text-align: center;
            margin-bottom: 40px;
            line-height: 1.6;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 24px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 8px;
        }

        label {
            font-size: 14px;
            font-weight: 500;
            color: #333;
            display: block;
        }

        .file-input-wrapper {
            position: relative;
            overflow: hidden;
        }

        input[type="file"] {
            display: none;
        }

        .file-input-label {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
            border: 2px dashed #667eea;
            border-radius: 8px;
            cursor: pointer;
            background-color: #f8f9ff;
            transition: all 0.3s ease;
            font-size: 14px;
            color: #667eea;
            font-weight: 500;
        }

        .file-input-label:hover {
            background-color: #eef0ff;
            border-color: #764ba2;
            color: #764ba2;
        }

        .file-input-label:focus-within {
            outline: none;
            background-color: #eef0ff;
            border-color: #764ba2;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        input[type="file"]:focus + .file-input-label {
            background-color: #eef0ff;
            border-color: #764ba2;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        .file-name {
            display: none;
            font-size: 13px;
            color: #667eea;
            margin-top: 8px;
            padding: 12px;
            background-color: #f0f4ff;
            border-radius: 6px;
            word-break: break-word;
        }

        .file-name.active {
            display: block;
        }

        .submit-btn {
            padding: 14px 24px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 12px;
        }

        .submit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 25px rgba(102, 126, 234, 0.3);
        }

        .submit-btn:active {
            transform: translateY(0);
        }

        .submit-btn:focus {
            outline: none;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.3);
        }

        .info-text {
            font-size: 12px;
            color: #999;
            text-align: center;
            margin-top: 20px;
            line-height: 1.5;
        }

        @media (max-width: 600px) {
            .container {
                padding: 40px 24px;
            }

            h1 {
                font-size: 24px;
            }

            .description {
                font-size: 14px;
            }

            .file-input-label {
                padding: 16px;
                font-size: 13px;
            }

            .submit-btn {
                padding: 12px 20px;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Convocation des Étudiants</h1>
    <p class="description">
        Importez un fichier texte contenant la liste des étudiants convoqués pour passer l'examen.
    </p>

    <form method="POST" action="${pageContext.request.contextPath}/callStudent">
        <div class="form-group">
            <label for="student-file">Sélectionnez un fichier texte</label>
            <div class="file-input-wrapper">
                <input
                        type="file"
                        id="student-file"
                        name="student-file"
                        accept=".txt"
                        required
                />
                <label for="student-file" class="file-input-label">
                    📄 Cliquez pour sélectionner un fichier .txt
                </label>
                <div class="file-name" id="file-name"></div>
            </div>
        </div>

        <button type="submit" class="submit-btn">Importer la Liste</button>

        <p class="info-text">
            Formats acceptés : fichiers texte (.txt) uniquement<br>
            Taille maximale recommandée : 10 MB
        </p>
    </form>
</div>

<script>
    const fileInput = document.getElementById('student-file');
    const fileNameDisplay = document.getElementById('file-name');

    fileInput.addEventListener('change', function() {
        if (this.files && this.files[0]) {
            const fileName = this.files[0].name;
            fileNameDisplay.textContent = '✓ Fichier sélectionné : ' + fileName;
            fileNameDisplay.classList.add('active');
        } else {
            fileNameDisplay.classList.remove('active');
        }
    });
</script>
</body>
</html>
