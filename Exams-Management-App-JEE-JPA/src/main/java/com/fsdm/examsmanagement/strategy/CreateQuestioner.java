package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.Questioner;

/**
 * Interface de base pour créer une question.
 * Chaque type de question a sa propre façon de construction.
 */
public interface CreateQuestioner {
    /**
     * Construit un objet question à partir d'une ligne de texte.
     *
     * @param line ligne lue depuis une source (fichier, formulaire, etc.)
     * @return objet question construit
     */
    Questioner construireQuestioner(String line);
}
