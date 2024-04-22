package io.cloudledger.aa.config.aws;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "amazon")
public class AmazonProperties {
    @NestedConfigurationProperty
    private Aws aws;

    @NestedConfigurationProperty
    private Cognito cognito;

    @Data
    public static class Aws {
        private String accessKeyId;
        private String accessKeySecret;
        private String defaultRegion;

    }

    @Data
    public static class Cognito {
        private String userPoolId;
        private String endpoint;
        private String identityPoolId;
        private String clientId;
    }
}
