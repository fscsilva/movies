package com.tenantevaluation.training.movies.adapter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.listener.RetryListenerSupport;

import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class RetryConfig {

    @Bean
    public List<RetryListener> retryListeners() {

        return Collections.singletonList(new RetryListenerSupport() {

            @Override
            public <T, E extends Throwable> void onError(
                    RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                log.warn("Retryable method {} threw {}th exception {}",
                        context.getAttribute("context.name"),
                        context.getRetryCount(), throwable.toString());
            }
        });
    }
}
