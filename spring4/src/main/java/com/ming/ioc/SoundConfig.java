package com.ming.ioc;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration

@ImportResource("classpath:spring-context.xml")
@ComponentScan(basePackageClasses = CompactDisc.class)
@Import(CDPlayerConfig.class)
@PropertySource("classpath:app.properties")
public class SoundConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
