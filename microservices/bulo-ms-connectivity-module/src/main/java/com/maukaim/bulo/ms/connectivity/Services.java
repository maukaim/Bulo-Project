package com.maukaim.bulo.ms.connectivity;

public class Services {
    public static Service SCHEDULER_SERVICE = new Service(
            "scheduler", "localhost", "10003");

    public static Service ORCHESTRATOR_SERVICE = new Service(
            "orchestrator", "localhost", "10004");

    public static Service FLOWS_SERVICE = new Service(
            "flows", "localhost", "10005");

    public static Service DEFINITIONS_SERVICE = new Service(
            "scheduler", "localhost", "10006");

    public static Service STAGES_SERVICE = new Service(
            "stages", "localhost", "10007");

    public static Service EXECUTORS_SERVICE = new Service(
            "executors", "localhost", "10008");
}
