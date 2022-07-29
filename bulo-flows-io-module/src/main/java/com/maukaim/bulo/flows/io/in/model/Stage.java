package com.maukaim.bulo.flows.io.in.model;

import com.maukaim.bulo.commons.models.IODefinition;
import com.maukaim.bulo.commons.models.FlowStageId;

import java.util.List;

/**
 * Quest ce que le Flow service a vraiment besoin de savoir
 */
public class Stage {
    private FlowStageId flowStageId; // Besoin pour Store
    private String stageModelId; // Besoin pour Store
    private List<IODefinition> inputTypes; // Besoin pour Valider. Ne verifie pas que ca existe, mais plutot que ca match avec la Stage d'avant
    private List<IODefinition> outputTypes; // Besoin pour Valider. Ne verifie pas que ca existe, mais plutot que ca match avec la Stage d'apres
}
