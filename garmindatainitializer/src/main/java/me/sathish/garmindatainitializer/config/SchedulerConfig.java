package me.sathish.garmindatainitializer.config;

import javax.sql.DataSource;
import me.sathish.garmindatainitializer.ApplicationProperties;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
class SchedulerConfig {
    private final ApplicationProperties props;

    SchedulerConfig(ApplicationProperties props) {
        this.props = props;
    }

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(JdbcTemplateLockProvider.Configuration.builder()
                .withJdbcTemplate(new JdbcTemplate(dataSource))
                .withTableName(props.db().shedlockTableName())
                .usingDbTime()
                .build());
    }
}
