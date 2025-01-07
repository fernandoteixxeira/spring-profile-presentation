package com.github.fernandoteixxeira.multipleprofiles;

import com.github.fernandoteixxeira.multipleprofiles.config.ActiveProperties;
import com.github.fernandoteixxeira.multipleprofiles.config.PersonProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PersonProperties.class, ActiveProperties.class})
public class MultipleProfilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleProfilesApplication.class, args);
    }

}
