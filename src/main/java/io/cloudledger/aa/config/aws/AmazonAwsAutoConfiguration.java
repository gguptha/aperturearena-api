//package io.cloudledger.aa.config.aws;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//
//@Lazy
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//@EnableConfigurationProperties(AmazonProperties.class)
//public class AmazonAwsAutoConfiguration {
//    private final AmazonProperties amazonProperties;
//
//    private AWSStaticCredentialsProvider provider;
//
//    @PostConstruct
//    public void init(){
//        provider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonProperties.getAws().getAccessKeyId(), amazonProperties.getAws().getAccessKeySecret()));
//    }
//
//    @Bean
//    public AWSCognitoIdentityProvider awsCognitoIdentityProvider() {
//        log.info("AWS Cognito : Initializing @ {}", amazonProperties.getAws().getDefaultRegion());
//        return AWSCognitoIdentityProviderClientBuilder.standard()
//                .withCredentials(provider)
//                .withRegion(Regions.fromName(amazonProperties.getAws().getDefaultRegion()))
//                .build();
//    }
//}
