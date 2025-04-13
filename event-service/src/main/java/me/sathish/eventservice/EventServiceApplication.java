package me.sathish.eventservice;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import me.sathish.eventservice.domain.util.DomainDataServiceUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableJpaRepositories(
        value = {"me.sathish.eventservice.domain.repo", "me.sathish.eventservice.events.repo"},
        repositoryBaseClass = BaseJpaRepositoryImpl.class)
@Slf4j
public class EventServiceApplication implements CommandLineRunner {
    final DomainDataServiceUtil domainDataServiceUtil;

    public EventServiceApplication(DomainDataServiceUtil domainDataServiceUtil) {
        this.domainDataServiceUtil = domainDataServiceUtil;
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(EventServiceApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info(
                """
                        Access URLs:
                        ----------------------------------------------------------
                        \tLocal: \t\t\thttp://localhost:{}
                        \tExternal: \t\thttp://{}:{}
                        \tEnvironment: \t{}\s
                        ----------------------------------------------------------""",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                Arrays.toString(env.getActiveProfiles()));
    }

    @Override
    public void run(String... args) throws Exception {
        domainDataServiceUtil.loadDomainData();
    }
}

@Controller
class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
