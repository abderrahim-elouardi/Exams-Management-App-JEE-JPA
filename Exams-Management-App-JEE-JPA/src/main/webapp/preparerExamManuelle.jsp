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
    <title>Créateur d'Examen</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            --primary: #2563eb;
            --primary-dark: #1d4ed8;
            --secondary: #64748b;
            --success: #10b981;
            --warning: #f59e0b;
            --error: #ef4444;
            --light: #f8fafc;
            --light-2: #e2e8f0;
            --dark: #1e293b;
            --text: #334155;
            --border: #cbd5e1;
            --shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
            --shadow-lg: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
            background: linear-gradient(135deg, #f0f9ff 0%, #f3e8ff 100%);
            color: var(--text);
            padding: 2rem 1rem;
            min-height: 100vh;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
        }

        .header {
            text-align: center;
            margin-bottom: 3rem;
        }

        .header h1 {
            font-size: 2.5rem;
            color: var(--dark);
            margin-bottom: 0.5rem;
            font-weight: 700;
        }

        .header p {
            font-size: 1.1rem;
            color: var(--secondary);
        }

        form {
            background: white;
            border-radius: 12px;
            padding: 2rem;
            box-shadow: var(--shadow-lg);
        }

        .form-section {
            margin-bottom: 2.5rem;
            padding-bottom: 2.5rem;
            border-bottom: 1px solid var(--light-2);
        }

        .form-section:last-child {
            border-bottom: none;
            margin-bottom: 0;
            padding-bottom: 0;
        }

        .section-title {
            font-size: 1.3rem;
            font-weight: 600;
            color: var(--dark);
            margin-bottom: 1.5rem;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .section-title::before {
            content: '';
            width: 4px;
            height: 24px;
            background: var(--primary);
            border-radius: 2px;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group:last-child {
            margin-bottom: 0;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: var(--dark);
            font-size: 0.95rem;
        }

        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 0.75rem 1rem;
            border: 2px solid var(--border);
            border-radius: 8px;
            font-size: 1rem;
            font-family: inherit;
            transition: all 0.3s ease;
            background: white;
        }

        input[type="text"]:focus,
        input[type="date"]:focus,
        select:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
        }

        input[type="text"]::placeholder {
            color: var(--secondary);
        }

        /* Grille pour les champs côte à côte */
        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1rem;
        }

        @media (max-width: 640px) {
            .form-row {
                grid-template-columns: 1fr;
            }
        }

        /* Section Checkboxes */
        .checkbox-group {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .checkbox-item {
            display: flex;
            align-items: flex-start;
            gap: 0.75rem;
            padding: 1rem;
            border: 2px solid var(--border);
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
            background: white;
        }

        .checkbox-item:hover {
            border-color: var(--primary);
            background: rgba(37, 99, 235, 0.02);
        }

        .checkbox-item input[type="checkbox"] {
            width: 20px;
            height: 20px;
            margin-top: 2px;
            cursor: pointer;
            accent-color: var(--primary);
        }

        .checkbox-label {
            flex: 1;
            cursor: pointer;
        }

        .checkbox-label-title {
            font-weight: 600;
            color: var(--dark);
            display: block;
            margin-bottom: 0.25rem;
        }

        .checkbox-label-desc {
            font-size: 0.9rem;
            color: var(--secondary);
        }

        /* Section Select */
        .select-group {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1rem;
            align-items: flex-end;
        }

        @media (max-width: 640px) {
            .select-group {
                grid-template-columns: 1fr;
            }
        }

        /* Sections d'ajout de questions */
        .question-types {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 1.5rem;
        }

        .question-card {
            border: 2px solid var(--light-2);
            border-radius: 8px;
            padding: 1.5rem;
            background: var(--light);
            transition: all 0.3s ease;
        }

        .question-card:hover {
            border-color: var(--primary);
            box-shadow: var(--shadow);
        }

        .question-card-title {
            font-weight: 600;
            color: var(--dark);
            margin-bottom: 0.75rem;
            font-size: 1rem;
        }

        .question-card-type {
            display: inline-block;
            background: var(--primary);
            color: white;
            padding: 0.25rem 0.75rem;
            border-radius: 20px;
            font-size: 0.75rem;
            font-weight: 600;
            margin-bottom: 1rem;
        }

        /* Type 1: QCM */
        .qcm-options {
            display: flex;
            flex-direction: column;
            gap: 0.75rem;
        }

        .option-item {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            padding: 0.75rem;
            background: white;
            border: 1px solid var(--border);
            border-radius: 6px;
        }

        .option-item input[type="radio"] {
            cursor: pointer;
            accent-color: var(--primary);
        }

        .option-item label {
            margin: 0;
            cursor: pointer;
            flex: 1;
            font-weight: normal;
        }

        /* Type 2: Réponse courte */
        .short-answer {
            padding: 1rem;
            background: white;
            border: 1px solid var(--border);
            border-radius: 6px;
            min-height: 60px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: var(--secondary);
            font-style: italic;
            font-size: 0.9rem;
        }

        /* Type 3: Espaces à remplir */
        .fill-blank-section {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .blank-text {
            padding: 1rem;
            background: white;
            border: 1px solid var(--border);
            border-radius: 6px;
            line-height: 1.6;
            color: var(--text);
        }

        .blank-text .blank {
            background: var(--light-2);
            padding: 0.25rem 0.5rem;
            border-radius: 4px;
            font-weight: 500;
        }

        .responses-list {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .response-item {
            display: flex;
            align-items: center;
            gap: 0.75rem;
            padding: 0.75rem;
            background: white;
            border: 1px solid var(--border);
            border-radius: 6px;
        }

        .response-item input[type="checkbox"] {
            cursor: pointer;
            accent-color: var(--success);
        }

        .response-item label {
            margin: 0;
            cursor: pointer;
            flex: 1;
            font-weight: normal;
        }

        .response-badge {
            display: inline-block;
            padding: 0.25rem 0.5rem;
            border-radius: 4px;
            font-size: 0.75rem;
            font-weight: 600;
        }

        .response-badge.correct {
            background: rgba(16, 185, 129, 0.2);
            color: var(--success);
        }

        .response-badge.incorrect {
            background: rgba(239, 68, 68, 0.2);
            color: var(--error);
        }

        /* Boutons */
        .button-group {
            display: flex;
            gap: 1rem;
            justify-content: flex-end;
            margin-top: 2rem;
            padding-top: 2rem;
            border-top: 1px solid var(--light-2);
        }

        @media (max-width: 640px) {
            .button-group {
                flex-direction: column;
            }
        }

        button {
            padding: 0.875rem 2rem;
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            font-family: inherit;
        }

        .btn-primary {
            background: var(--primary);
            color: white;
        }

        .btn-primary:hover {
            background: var(--primary-dark);
            box-shadow: var(--shadow);
            transform: translateY(-2px);
        }

        .btn-secondary {
            background: var(--light-2);
            color: var(--dark);
        }

        .btn-secondary:hover {
            background: var(--border);
        }

        .btn-add {
            background: rgba(16, 185, 129, 0.1);
            color: var(--success);
            border: 2px solid var(--success);
            width: 100%;
            padding: 1rem;
            margin-top: 1rem;
            font-weight: 600;
        }

        .btn-add:hover {
            background: rgba(16, 185, 129, 0.2);
        }

        /* Responsive */
        @media (max-width: 768px) {
            .header h1 {
                font-size: 2rem;
            }

            form {
                padding: 1.5rem;
            }

            .question-types {
                grid-template-columns: 1fr;
            }
        }



        .hidden {
            display: none;
        }

        .selectElementClasse{
            display: inline-block;
        }

        .options {
            margin-top: 10px;
            display: flex;
            flex-direction: column;
            gap: 8px;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", ()=>{

            document.getElementById("QCMCheckBox").addEventListener("click", ()=>{
                const checkbox = document.getElementById("QCMCheckBox");
                if (checkbox.checked) {
                    select = document.getElementById("selectionQcm");
                    select.classList.remove("hidden");
                } else {
                    select = document.getElementById("selectionQcm");
                    select.classList.add("hidden");
                }
            });
            document.getElementById("selectionQcm").addEventListener("change", ()=>{
                document.getElementById("QcmQuestions").innerHTML = "";
                const select = document.getElementById("selectionQcm");
                const value = select.value;
                questionArea = document.getElementById("QcmQuestions");
                questionArea.innerHTML = "";
                for(let i = 0; i < value; i++){
                    questionArea.innerHTML += `
<h3>Question ${i+1}</h3>

<input type="text" name="QCMquestion_${i}" placeholder="Enoncé de la question"/>

<div class="options">
    <input type="text" name="QCMq${i}_opt1" placeholder="Option 1"/>
    <input type="text" name="QCMq${i}_opt2" placeholder="Option 2"/>
    <input type="text" name="QCMq${i}_opt3" placeholder="Option 3"/>
    <input type="text" name="QCMq${i}_opt4" placeholder="Option 4"/>
</div>
<div class="form-section">
        <h2 class="section-title">Configuration</h2>

        <div class="select-group">
          <div class="form-group">
            <label for="question-count">Nombre de Points pour cette question. *</label>
            <select id="question-count" name="question_count" required>
              <option value="">Sélectionnez un nombre</option>
              <option value="1">1</option>
              <option value="5">2</option>
              <option value="5">3</option>
              <option value="5">4</option>
              <option value="5">5</option>
              <option value="5">6</option>
              <option value="5">7</option>
              <option value="10">10</option>
              <option value="15">15</option>
              <option value="20">20</option>
              <option value="25">25</option>
              <option value="30">30</option>
            </select>
          </div>
        </div>
      </div>
`
                }
            });

            document.getElementById("ShortAnswerCheckBox").addEventListener("click", ()=>{
                const checkbox = document.getElementById("ShortAnswerCheckBox");
                if (checkbox.checked) {
                    select = document.getElementById("selectionShortAnswer");
                    select.classList.remove("hidden");
                } else {
                    select = document.getElementById("selectionShortAnswer");
                    select.classList.add("hidden");
                }
            });


            document.getElementById("selectionShortAnswer").addEventListener("change", ()=>{
                document.getElementById("ShortAnswerQuestions").innerHTML = "";
                const select = document.getElementById("selectionShortAnswer");
                const value = select.value;
                questionArea = document.getElementById("ShortAnswerQuestions");
                questionArea.innerHTML = "";
                for(let i = 0; i < value; i++){
                    questionArea.innerHTML += `
<h3>Question ${i+1}</h3>

<input type="text" name="ShortAnswerquestion_${i}" placeholder="Enoncé de la question"/>

<div class="options">
    <input type="text" name="ShortAnswerq${i}_opt1" placeholder="Answer"/>
</div>
<div class="form-section">
        <h2 class="section-title">Configuration</h2>

        <div class="select-group">
          <div class="form-group">
            <label for="question-count">Nombre de Points pour cette question. *</label>
            <select id="question-count" name="question_count" required>
              <option value="">Sélectionnez un nombre</option>
              <option value="1">1</option>
              <option value="5">2</option>
              <option value="5">3</option>
              <option value="5">4</option>
              <option value="5">5</option>
              <option value="5">6</option>
              <option value="5">7</option>
              <option value="10">10</option>
              <option value="15">15</option>
              <option value="20">20</option>
              <option value="25">25</option>
              <option value="30">30</option>
            </select>
          </div>
        </div>
      </div>
      `
                }
            });


            document.getElementById("FillBlankCheckBox").addEventListener("click", ()=>{
                const checkbox = document.getElementById("FillBlankCheckBox");
                if (checkbox.checked) {
                    select = document.getElementById("selectionFillBlank");
                    select.classList.remove("hidden");
                } else {
                    select = document.getElementById("selectionFillBlank");
                    select.classList.add("hidden");
                }
            });

            document.getElementById("selectionFillBlank").addEventListener("change", ()=>{
                document.getElementById("FillBlankQuestions").innerHTML = "";
                const select = document.getElementById("selectionFillBlank");
                const value = select.value;
                questionArea = document.getElementById("FillBlankQuestions");
                questionArea.innerHTML = "";
                for(let i = 0; i < value; i++){
                    questionArea.innerHTML += `
<h3>Question ${i+1} (Chaque blanc doit être complété par quatre points (....).)</h3>

<input type="text" name="FillBlankquestion_${i}" placeholder="Enoncé de la question"/>

<div class="options">
    <input type="text" name="FillBlankq${i}_opt1" placeholder="Answer"/>
</div>
<div class="form-section">
        <h2 class="section-title">Configuration</h2>

        <div class="select-group">
          <div class="form-group">
            <label for="question-count">Nombre de Points pour cette question. *</label>
            <select id="question-count" name="question_count" required>
              <option value="">Sélectionnez un nombre</option>
              <option value="1">1</option>
              <option value="5">2</option>
              <option value="5">3</option>
              <option value="5">4</option>
              <option value="5">5</option>
              <option value="5">6</option>
              <option value="5">7</option>
              <option value="10">10</option>
              <option value="15">15</option>
              <option value="20">20</option>
              <option value="25">25</option>
              <option value="30">30</option>
            </select>
          </div>
        </div>
      </div>
`
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Créateur d'Examen</h1>
        <p>Créez votre examen de manière simple et efficace</p>
    </div>

    <form action="${pageContext.request.contextPath}/preparerExamManuelleController" method="POST">
        <!-- Section 1: Informations générales -->
        <div class="form-section">
            <h2 class="section-title">Informations Générales</h2>

            <div class="form-group">
                <label for="exam-title">Titre de l'Examen *</label>
                <input type="text" id="exam-title" name="exam_title" placeholder="Ex: Mathématiques - Chapitre 5"/>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="exam-date">Date Limite *</label>
                    <input type="date" id="exam-date" name="exam_date"/>
                </div>
                <div class="form-group">
                    <label for="exam-desc">Description</label>
                    <input type="text" id="exam-desc" name="exam_description" placeholder="Description optionnelle de l'examen"/>
                </div>
            </div>
        </div>

        <!-- Section 2: Types de questions -->
        <div class="form-section">
            <h2 class="section-title">Types de Questions à Inclure</h2>

            <div class="checkbox-group">
                <label class="checkbox-item">
                    <input type="checkbox" name="QCMquestion_typesCheckBox" value="qcm" id="QCMCheckBox"/>
                    <span class="checkbox-label">
              <span class="checkbox-label-title">Type 1 : QCM avec plusieurs réponses</span>
              <span class="checkbox-label-desc">L'étudiant sélectionne la bonne réponse parmi plusieurs options</span>
            </span>
                    <select  id="selectionQcm" name="numberOfQcmQuestion" class="hidden" >
                        <option value="1">1</option>
                        <option value="5">5</option>
                    </select>
                </label>
                <div id="QcmQuestions">

                </div>
                <label class="checkbox-item">
                    <input type="checkbox" name="SHORTquestion_typesCheckBox" value="short_answer" id="ShortAnswerCheckBox"/>
                    <span class="checkbox-label">
              <span class="checkbox-label-title">Type 2 : Question à courte réponse</span>
              <span class="checkbox-label-desc">L'étudiant rédige une réponse courte ou phrase</span>
            </span>
                    <select  id="selectionShortAnswer" name="numberOfShortAnswerQuestion" class="hidden" >
                        <option value="1">1</option>
                        <option value="5">5</option>
                    </select>
                </label>
                <div id="ShortAnswerQuestions">

                </div>

                <label class="checkbox-item">
                    <input type="checkbox" name="FILLBLANKquestion_typesCheckBox" value="fill_blank" id="FillBlankCheckBox"/>
                    <span class="checkbox-label">
              <span class="checkbox-label-title">Type 3 : Question avec espaces à remplir</span>
              <span class="checkbox-label-desc">L'étudiant complète les blancs avec des propositions</span>
            </span>
                    <select id="selectionFillBlank" name="numberOfFillBlankQuestion" class="hidden" >
                        <option value="1">1</option>
                        <option value="5">5</option>
                    </select>
                </label>
                <div id="FillBlankQuestions">

                </div>
            </div>
        </div>

        <!-- Boutons d'action -->
        <div class="button-group">
            <button type="reset" class="btn-secondary">Annuler</button>
            <button type="submit" class="btn-primary">Créer l'Examen</button>
        </div>
    </form>
</div>
</body>
</html>
