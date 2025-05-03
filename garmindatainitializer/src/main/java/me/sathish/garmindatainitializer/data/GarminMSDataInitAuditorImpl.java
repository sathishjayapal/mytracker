package me.sathish.garmindatainitializer.data;

import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class GarminMSDataInitAuditorImpl implements AuditorAware<BigInteger> {

    @Override
    public Optional<BigInteger> getCurrentAuditor() {
        return Optional.of(BigInteger.ONE);
    }
}
