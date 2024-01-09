package service.notification.models;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    private static final String AMAZON_QUEUE = "amazonJobQueue";
    private static final String APPLE_QUEUE = "appleJobQueue";
    private static final String FACEBOOK_QUEUE = "facebookJobQueue";
    private static final String GOOGLE_QUEUE = "googleJobQueue";
    private static final String NETFLIX_QUEUE = "netflixJobQueue";

    @Bean
    public Queue amazonJobQueue() {
        return new Queue(AMAZON_QUEUE);
    }

    @Bean
    public Queue appleJobQueue() {
        return new Queue(APPLE_QUEUE);
    }

    @Bean
    public Queue facebookJobQueue() {
        return new Queue(FACEBOOK_QUEUE);
    }

    @Bean
    public Queue googleJobQueue() {
        return new Queue(GOOGLE_QUEUE);
    }

    @Bean
    public Queue netflixJobQueue() {
        return new Queue(NETFLIX_QUEUE);
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(amazonJobQueue());
        rabbitAdmin.declareQueue(appleJobQueue());
        rabbitAdmin.declareQueue(facebookJobQueue());
        rabbitAdmin.declareQueue(googleJobQueue());
        rabbitAdmin.declareQueue(netflixJobQueue());
        return rabbitAdmin;
    }
}
