package me.sathish.trackgarmin.config;

import com.netflix.discovery.shared.resolver.ClusterResolver;
import com.netflix.discovery.shared.resolver.EurekaEndpoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class MyClusterResolver implements ClusterResolver<EurekaEndpoint> {

    private final List<EurekaEndpoint> endpoints;

    public MyClusterResolver() {
        this.endpoints = new ArrayList<>();
        // Add your Eureka servers here
         this.endpoints.add(new EurekaEndpoint() {
             @Override
             public int compareTo(@NotNull Object o) {
                 return 0;
             }

             @Override
             public String getServiceUrl() {
                 return "";
             }

             @Override
             public String getHostName() {
                 return "";
             }

             @Override
             public String getNetworkAddress() {
                 return "";
             }

             @Override
             public int getPort() {
                 return 0;
             }

             @Override
             public boolean isSecure() {
                 return false;
             }

             @Override
             public String getRelativeUri() {
                 return "http://sathish:pass@localhost:8070/eureka/";
             }
         });
    }

    @Override
    public String getRegion() {
        return ""; // replace with your region
    }

    @Override
    public List<EurekaEndpoint> getClusterEndpoints() {
        return endpoints;
    }
}
