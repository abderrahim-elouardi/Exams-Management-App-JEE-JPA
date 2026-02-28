<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Passer l'examen</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Helvetica Neue', sans-serif;
            background: #f5f7fa;
            color: #1f2937;
            padding: 24px;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background: #ffffff;
            border-radius: 14px;
            box-shadow: 0 10px 24px rgba(0, 0, 0, 0.08);
            overflow: hidden;
        }

        .header {
            padding: 20px;
            background: linear-gradient(90deg, #1a1a2e 0%, #16213e 100%);
            color: #ffffff;
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 10px;
            flex-wrap: wrap;
        }

        .exam-title {
            font-size: 1.4rem;
            font-weight: 700;
        }

        .timer {
            background: rgba(255, 255, 255, 0.15);
            padding: 10px 14px;
            border-radius: 10px;
            font-weight: 600;
        }

        .content {
            padding: 24px;
        }

        .question-card {
            display: none;
            border: 1px solid #e5e7eb;
            border-radius: 12px;
            padding: 18px;
            background: #f9fafb;
        }

        .question-card.active {
            display: block;
        }

        .question-meta {
            color: #374151;
            font-weight: 600;
            margin-bottom: 10px;
        }

        .question-text {
            font-size: 1.06rem;
            margin-bottom: 16px;
        }

        .options,
        .blank-inputs {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .option {
            display: flex;
            align-items: center;
            gap: 10px;
            padding: 10px 12px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            background: #ffffff;
        }

        .blank-inputs input,
        .short-input {
            width: 100%;
            padding: 10px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            font-size: 0.95rem;
        }

        .actions {
            margin-top: 18px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

        .btn {
            border: none;
            border-radius: 8px;
            padding: 10px 16px;
            font-weight: 600;
            cursor: pointer;
        }

        .btn-primary {
            background: #2563eb;
            color: #ffffff;
        }

        .btn-success {
            background: #16a34a;
            color: #ffffff;
        }

        .message {
            margin-top: 14px;
            font-weight: 600;
            color: #b91c1c;
        }

        .empty {
            padding: 14px;
            border: 1px solid #e5e7eb;
            border-radius: 10px;
            background: #f9fafb;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="exam-title">${exam.titre}</div>
        <div class="timer">Temps restant : <span id="countdown">--:--</span></div>
    </div>

    <div class="content">
        <c:choose>
            <c:when test="${empty questions}">
                <div class="empty">Aucune question disponible pour cet examen.</div>
            </c:when>
            <c:otherwise>
                <form id="examForm" action="${pageContext.request.contextPath}/finishExamStudent" method="post">
                    <input type="hidden" name="idExam" value="${exam.idExam}">
                    <c:forEach var="question" items="${questions}" varStatus="status">
                        <div class="question-card ${status.first ? 'active' : ''}" data-index="${status.index}">
                            <div class="question-meta">Question ${status.index + 1} / ${questions.size()}</div>
                            <div class="question-text">${question.question}</div>

                            <c:choose>
                                <c:when test="${question.class.simpleName == 'QCM'}">
                                    <div class="options">
                                        <c:forEach var="answer" items="${question.answerList}">
                                            <label class="option">
                                                <input type="checkbox" name="q_${question.id}" value="${answer.id}">
                                                <span>${answer.answer}</span>
                                            </label>
                                        </c:forEach>
                                    </div>
                                </c:when>

                                <c:when test="${question.class.simpleName == 'QFillInBlank'}">
                                    <div class="blank-inputs fill-blank-container" data-question-id="${question.id}">
                                    </div>
                                </c:when>

                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${not empty question.answer}">
                                            <div class="options">
                                                <label class="option">
                                                    <input type="checkbox" name="q_${question.id}" value="${question.answer.id}">
                                                    <span>${question.answer.answer}</span>
                                                </label>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="short-input" type="text" name="q_${question.id}_short" placeholder="Votre réponse">
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>

                    <div class="actions">
                        <button type="button" class="btn btn-primary" id="nextBtn">Suivant</button>
                        <button type="button" class="btn btn-success" id="finishBtn" style="display:none;">Terminer</button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>

        <div id="statusMessage" class="message"></div>
    </div>
</div>

<script>
    (function () {
        const cards = Array.from(document.querySelectorAll('.question-card'));
        const nextBtn = document.getElementById('nextBtn');
        const finishBtn = document.getElementById('finishBtn');
        const statusMessage = document.getElementById('statusMessage');
        const countdownEl = document.getElementById('countdown');
        const examForm = document.getElementById('examForm');

        let currentIndex = 0;
        let examStopped = false;
        const durationMinutes = Number('${durationMinutes}');
        let remainingSeconds = Number.isFinite(durationMinutes) && durationMinutes > 0 ? durationMinutes * 60 : 30 * 60;

        function formatTime(totalSeconds) {
            const minutes = Math.floor(totalSeconds / 60);
            const seconds = totalSeconds % 60;
            return String(minutes).padStart(2, '0') + ':' + String(seconds).padStart(2, '0');
        }

        function updateTimerDisplay() {
            if (countdownEl) {
                countdownEl.textContent = formatTime(Math.max(remainingSeconds, 0));
            }
        }

        function initializeFillInBlankInputs() {
            const fillBlankContainers = Array.from(document.querySelectorAll('.fill-blank-container'));

            fillBlankContainers.forEach((container) => {
                const questionCard = container.closest('.question-card');
                const questionTextEl = questionCard ? questionCard.querySelector('.question-text') : null;
                const questionText = questionTextEl ? questionTextEl.textContent : '';
                const questionId = container.getAttribute('data-question-id');

                const matches = questionText ? questionText.match(/\.{4,}/g) : null;
                const blankCount = matches && matches.length > 0 ? matches.length : 1;

                for (let index = 0; index < blankCount; index += 1) {
                    const input = document.createElement('input');
                    input.type = 'text';
                    input.name = 'q_' + questionId + '_blank_' + index;
                    input.placeholder = 'Réponse ' + (index + 1);
                    container.appendChild(input);
                }
            });
        }

        function showQuestion(index) {
            cards.forEach((card, i) => {
                card.classList.toggle('active', i === index);
            });

            if (index >= cards.length - 1) {
                nextBtn.style.display = 'none';
                finishBtn.style.display = 'inline-block';
            } else {
                nextBtn.style.display = 'inline-block';
                finishBtn.style.display = 'none';
            }
        }

        function stopExam(message) {
            if (examStopped) {
                return;
            }

            examStopped = true;
            if (statusMessage) {
                statusMessage.textContent = message;
            }

            if (nextBtn) {
                nextBtn.disabled = true;
            }
            if (finishBtn) {
                finishBtn.disabled = true;
            }

            setTimeout(function () {
                if (examForm) {
                    examForm.submit();
                } else {
                    window.location.href = '${pageContext.request.contextPath}/afterLoginStudent';
                }
            }, 900);
        }

        updateTimerDisplay();
        initializeFillInBlankInputs();

        if (cards.length > 0) {
            showQuestion(currentIndex);
        } else {
            if (nextBtn) nextBtn.style.display = 'none';
            if (finishBtn) finishBtn.style.display = 'none';
        }

        if (nextBtn) {
            nextBtn.addEventListener('click', function () {
                if (examStopped) {
                    return;
                }

                if (currentIndex < cards.length - 1) {
                    currentIndex += 1;
                    showQuestion(currentIndex);
                }
            });
        }

        if (finishBtn) {
            finishBtn.addEventListener('click', function () {
                stopExam('Examen terminé.');
            });
        }

        const timerInterval = setInterval(function () {
            if (examStopped) {
                clearInterval(timerInterval);
                return;
            }

            remainingSeconds -= 1;
            updateTimerDisplay();

            if (remainingSeconds <= 0) {
                clearInterval(timerInterval);
                stopExam('Temps écoulé, examen arrêté automatiquement.');
            }
        }, 1000);
    })();
</script>
</body>
</html>