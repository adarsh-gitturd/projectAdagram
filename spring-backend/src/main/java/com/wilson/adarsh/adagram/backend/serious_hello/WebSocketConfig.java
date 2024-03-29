package com.wilson.adarsh.adagram.backend.serious_hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // private final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Override
    public void configureMessageBroker(@SuppressWarnings("null") MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/user");
        // Server -> Client
        config.setApplicationDestinationPrefixes("/app");
        // // Client -> Server
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(@SuppressWarnings("null") StompEndpointRegistry registry) {
        registry.addEndpoint("/abc").
            setAllowedOriginPatterns("*").withSockJS();
        // clients connect to this endpoint to establish a 
        // connection with da server
    }
}

// Explanation:
// enableSimpleBroker("/topic"):

// This line enables a simple in-memory message broker. It means that 
// the server can broadcast messages to all connected clients on 
// destinations prefixed with "/topic". Clients can subscribe to
//  these topics to receive updates.

// setApplicationDestinationPrefixes("/app"):
// This line sets the application destination prefix to "/app". 
// It means that client destinations must be prefixed with "/app" 
// when sending messages to the server. For example, if a client
//  wants to send a message to a server method, the destination 
//  would be "/app/your-endpoint".
// Destinations:

// Clients can send messages to server-side methods by 
// using destinations prefixed with "/app" (e.g., "/app/your-endpoint").
//  The server can broadcast messages to all clients using
//   destinations prefixed with "/topic" (e.g., "/topic/some-topic").