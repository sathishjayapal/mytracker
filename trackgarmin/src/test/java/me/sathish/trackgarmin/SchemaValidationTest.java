package me.sathish.trackgarmin;

import me.sathish.trackgarmin.common.TestAuditingConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.jpa.hibernate.ddl-auto=create",
            "spring.jpa.properties.hibernate.format_sql=true",
            "spring.jpa.properties.hibernate.show_sql=true",
            "spring.jpa.properties.hibernate.use_sql_comments=true",
            "spring.jpa.paroperties.hibernate.type=trace",
            "spring.datasource.url=jdbc:tc:postgresql:16.0-alpine:///db",
        })
@Import({TestAuditingConfig.class})
class SchemaValidationTest {
    @Autowired
    private AuditorAware<String> testAuditProvider;

    @Test
    void validateJpaMappingsWithDbSchema() {
        System.out.println("Schema validation test");
    }
}
