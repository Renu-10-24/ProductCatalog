package dev.ren.productCatalog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app.service")
public class ServiceConfigWithConfigProcessor {
    private String name;
    private int port;
    private List<String> paths;
    // ... This still works perfectly!

    public int getPort() {
        return port;
    }

    public List<String> getPaths() {
        return paths;
    }

    public String getName() {
        return name;
    }
}
