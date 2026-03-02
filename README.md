# 🎓 Exams Management App - Générateur d'Examen Quizz

Ce projet consiste en le développement d'une application web robuste pour la **génération et la passation d'examens en ligne**. [cite_start]Il a été réalisé dans le cadre du module **Programmation avancée et technologies JEE**[cite: 5, 6].

## 👥 Équipe du Projet
* [cite_start]**AQUAD Abdelkarim** [cite: 10]
* [cite_start]**EL OUARDI Abderrahim** [cite: 10]
* **Encadrant :** Pr. [cite_start]Yahyaouy Ali [cite: 11]
* [cite_start]**Établissement :** Faculté des Sciences Dhar El Mahraz - Fès [cite: 1]

---

## 🚀 Fonctionnalités Clés
[cite_start]L'application propose deux interfaces distinctes pour l'administrateur et l'étudiant[cite: 35, 50].

### 🛠️ Pour l'Administrateur
* [cite_start]**Configuration d'examens :** Définition du titre, de la durée, du nombre de questions et de la liste des candidats[cite: 55, 73, 74, 75].
* [cite_start]**Gestion des questions :** Support de plusieurs types : QCM (unique/multiple), textes à trous et courtes réponses[cite: 68, 69, 70, 71].
* **Modes d'alimentation :**
    * [cite_start]Saisie manuelle via une interface web dynamique[cite: 52].
    * [cite_start]Importation par fichier texte structuré[cite: 53, 79].
* [cite_start]**Gestion des étudiants :** Importation massive de la liste des étudiants via un fichier **Excel** (Nom, Prénom, Email)[cite: 54, 85, 86].
* [cite_start]**Diffusion :** Lancement de l'examen et envoi des accès par email[cite: 56, 92].

### ✍️ Pour l'Étudiant
* [cite_start]**Passation en ligne :** Accès sécurisé avec un affichage séquentiel et aléatoire des questions[cite: 57, 95].
* [cite_start]**Chronométrage :** Affichage d'un chronomètre durant toute la durée autorisée[cite: 57, 96].
* [cite_start]**Résultats :** Calcul automatique de la note et enregistrement immédiat en base de données[cite: 58, 98, 100].

---

## 🛠️ Stack Technique
[cite_start]L'application repose sur une architecture **MVC N-Tiers**[cite: 164, 166].

* [cite_start]**Back-end :** Java 17+, Jakarta Servlet, JSP (JSTL), EJB[cite: 179, 183, 186].
* [cite_start]**Persistance :** JPA / Hibernate avec base de données Oracle[cite: 181, 182, 186].
* [cite_start]**Front-end :** HTML5, CSS3, JavaScript pour la manipulation dynamique du DOM[cite: 184, 185].
* [cite_start]**Serveur d'applications :** WildFly 39 (JBoss)[cite: 186].
* [cite_start]**Outils :** Maven, IntelliJ IDEA, Git[cite: 186].

---

## 📂 Structure du Projet
* [cite_start]`src/main/java` : Contient les Entités JPA, les Servlets (Contrôleurs) et les modules DAO[cite: 191, 192, 194].
* [cite_start]`src/main/webapp` : Contient les pages JSP et les ressources statiques (CSS, JS)[cite: 168, 180].
* [cite_start]`export_database.sql` : Script complet de création de la base de données[cite: 147, 204].
* [cite_start]`Rapport_Projet_Generateur_Examen.pdf` : Documentation détaillée du projet[cite: 24].

---

## ⚙️ Installation Rapide
1. [cite_start]**Base de données :** Exécutez le script fourni dans `export_database.sql` sur votre instance Oracle/H2[cite: 204].
2. [cite_start]**Configuration :** Ajustez le fichier `persistence.xml` pour établir la connexion avec votre base de données[cite: 146, 176].
3. [cite_start]**Déploiement :** Compilez le projet avec Maven et déployez le fichier généré sur votre serveur WildFly[cite: 171, 186].

---
[cite_start]© 2026 - Master Web Intelligence et Science de données [cite: 3, 4, 12]
