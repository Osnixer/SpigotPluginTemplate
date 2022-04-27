package net.osnixer.template.config;

import net.dzikoysk.cdn.source.Resource;

public interface ConfigWithResource {

    Resource getResource();

    void setResource(Resource resource);

}

