package net.streamarchive.dbapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class HibernateSearchConfig {

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory, StreamSearchService streamSearchService) throws InterruptedException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        streamSearchService.initService(entityManager);
        return entityManager;
    }
}
