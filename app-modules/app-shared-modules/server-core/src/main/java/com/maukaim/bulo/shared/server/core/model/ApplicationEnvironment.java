package com.maukaim.bulo.shared.server.core.model;

public enum ApplicationEnvironment {
    dev,
    uat,
    prod;

    public static ApplicationEnvironment of(String profile){
        if(profile.equalsIgnoreCase(dev.name())){
            return dev;
        }else if(profile.equalsIgnoreCase(uat.name())){
            return uat;
        }else if(profile.equalsIgnoreCase(prod.name())){
            return prod;
        }
        return null;
    }
}
