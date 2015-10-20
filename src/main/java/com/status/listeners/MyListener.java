package com.status.listeners;

import com.status.utils.PerformanceMonitor;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by anteastra on 20.10.2015.
 */


@Component
public class MyListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    @Override
    public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent event) {
        PerformanceMonitor.port = event.getEmbeddedServletContainer().getPort();
    }
}
