package com.social.instagram.config;

import com.social.instagram.exception.AwsYmlNotPathException;
import com.social.instagram.exception.YamlFailLoadException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static com.social.instagram.util.aws.AwsConfigurationConstants.PATH_RESOURCE;
import static com.social.instagram.util.aws.AwsConfigurationConstants.PROPERTY_SOURCE_FIRST_GET;
import static com.social.instagram.util.aws.AwsConfigurationConstants.CUSTOM_RESOURCE;

import static com.social.instagram.util.aws.AwsResponseConstants.ERROR_MESSAGE_AWS_YML_FAIL_LOAD;
import static com.social.instagram.util.aws.AwsResponseConstants.ERROR_MESSAGE_AWS_YML_NOT_PATH;

public class AwsS3EnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        Resource path = new ClassPathResource(PATH_RESOURCE);
        PropertySource<?> propertySource = loadYaml(path);
        environment.getPropertySources().addLast(propertySource);
    }

    private PropertySource<?> loadYaml(Resource path) {
        validatePathExist(path);

        try {
            return this.loader.load(CUSTOM_RESOURCE, path).get(PROPERTY_SOURCE_FIRST_GET);
        } catch (IOException ex) {
            throw new YamlFailLoadException(ERROR_MESSAGE_AWS_YML_FAIL_LOAD);
        }

    }

    private void validatePathExist(Resource path) {
        if (!path.exists()) {
            throw new AwsYmlNotPathException(ERROR_MESSAGE_AWS_YML_NOT_PATH);
        }
    }

}