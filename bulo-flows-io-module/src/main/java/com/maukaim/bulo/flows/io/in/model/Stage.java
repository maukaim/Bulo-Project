package com.maukaim.bulo.flows.io.in.model;

import com.maukaim.bulo.commons.models.definitions.IODefinition;
import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.List;

/**
 * Quest ce que le Flow service a vraiment besoin de savoir
 */
public class Stage {
    private FlowStageId flowStageId; // Besoin pour Store
    private String stageModelId; // Besoin pour Store
    // Si il a deja le stageModelId en memoire, ca lui servira a rien ces deux suivant. Faire un choix entre avoir le stageModelId et les IO. Je pense le StageModelId. Quoi que, ca marche que pour les Tech. Stage du coup?
    private List<IODefinition> inputTypes; // Besoin pour Valider. Ne verifie pas que ca existe, mais plutot que ca match avec la Stage d'avant
    private List<IODefinition> outputTypes; // Besoin pour Valider. Ne verifie pas que ca existe, mais plutot que ca match avec la Stage d'apres
}
