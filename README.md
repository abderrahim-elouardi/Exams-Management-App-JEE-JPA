# 🎓 Exams Management App - Générateur d'Examen Quizz

Ce projet consiste en le développement d'une application web robuste pour la **génération et la passation d'examens en ligne**. Il a été réalisé dans le cadre du module **Programmation avancée et technologies JEE** (Master Web Intelligence et Science de données).

## 👥 Équipe du Projet
* **AQUAD Abdelkarim** & **EL OUARDI Abderrahim**
* **Encadrant :** Pr. Yahyaouy Ali
* **Établissement :** Faculté des Sciences Dhar El Mahraz - Fès
* **Année :** 2025-2026

---

## 🚀 Fonctionnalités Clés
L'application propose deux interfaces distinctes selon le rôle de l'utilisateur.

### 🛠️ Pour l'Administrateur
* **Configuration d'examens :** Définition du titre, de la durée, du nombre de questions et gestion des candidats.
* **Gestion des questions :** Support de plusieurs types : QCM (unique/multiple), textes à trous et courtes réponses.
* **Modes d'alimentation :**
    * Saisie manuelle via une interface web dynamique.
    * Importation par fichier texte structuré.
* **Gestion des étudiants :** Importation massive via un fichier **Excel**.
* **Diffusion :** Lancement de l'examen et envoi des accès par email.

### ✍️ Pour l'Étudiant
* **Passation en ligne :** Accès sécurisé avec affichage séquentiel et aléatoire des questions.
* **Chronométrage :** Affichage d'un compte à rebours en temps réel.
* **Résultats :** Calcul automatique de la note et enregistrement immédiat en base de données.

---

## 🛠️ Stack Technique
L'application repose sur une architecture **N-Tiers (MVC)** déployée sur le serveur **WildFly**.

* **Back-end :** Java 17+, Jakarta Servlet, JSP (JSTL), EJB.
* **Persistance :** JPA / Hibernate avec base de données **Oracle** (ou H2).
* **Front-end :** HTML5, CSS3, JavaScript.
* **Serveur d'applications :** WildFly 39 (JBoss).
* **Outils :** Maven, IntelliJ IDEA, Git.

---

## 📂 Structure du Projet
* `src/main/java` : Entités JPA, Servlets et services DAO.
* `src/main/webapp` : Pages JSP et ressources statiques.
* `export_database.sql` : Script SQL pour la reconstruction de la base de données.
* `Rapport_Projet_Generateur_Examen.pdf` : Documentation technique complète.

---

## ⚙️ Installation Rapide
1. **Base de données :** Importez le script `export_database.sql` pour créer les tables.
2. **Configuration :** Ajustez le fichier `persistence.xml` avec vos identifiants.
3. **Déploiement :** Compilez avec Maven (`mvn clean package`) et déployez le `.war` sur WildFly.

---
© 2026 - Master Web Intelligence et Science de données.
