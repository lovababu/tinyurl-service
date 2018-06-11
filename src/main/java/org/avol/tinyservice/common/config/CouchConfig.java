package org.avol.tinyservice.common.config;

import com.couchbase.client.java.PersistTo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Durga, Padala on 10/06/18.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "urlapi.couchbase")
public class CouchConfig {

    private List<String> bootstrapHosts;

    private int defaultExpiryInDays;

    private final BucketConfig bucket = new BucketConfig();

    @Getter
    @Setter
    public static class BucketConfig {

        private String name;

        private String password;

        private PersistTo persistFactor;
    }
}
