package com.flashsaleproject.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * Customizes the embedded Tomcat web server configuration.
 * 
 * This config sets Keep-Alive timeout settings to improve performance
 * and manage client connection behavior.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */

@Component
public class WebServerConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        if (factory instanceof TomcatServletWebServerFactory) {
            ((TomcatServletWebServerFactory) factory).addConnectorCustomizers(new TomcatConnectorCustomizer() {
                @Override
                public void customize(Connector connector) {
                    Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();

                    // Disconnect keep-alive connections if no request within 30 seconds
                    protocol.setKeepAliveTimeout(30000);

                    // Limit max keep-alive requests to 10,000 per connection
                    protocol.setMaxKeepAliveRequests(10000);
                }
            });
        }
    }
}
