package net.streamarchive.dbapi;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class StreamSearchService {
    @Autowired
    EntityManager entityManager;
    QueryBuilder queryBuilder;

    public void initService(EntityManager entityManager) throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

    }

    public List findStreams(String streamer, String queryString) {
        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);
        queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Stream.class)
                .get();
        org.apache.lucene.search.Query query = queryBuilder.bool().must(
                queryBuilder.bool().should(queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(2)
                        .withPrefixLength(0)
                        .onFields("title", "game")
                        .matching(queryString)
                        .createQuery()).should(queryBuilder
                        .keyword()
                        .wildcard()
                        .onFields("title", "game")
                        .matching(queryString + "*")
                        .createQuery()).createQuery()).must(queryBuilder
                .keyword()
                .onField("streamer")
                .matching(streamer)
                .createQuery()).createQuery();

        javax.persistence.Query persistenceQuery =
                fullTextEntityManager.createFullTextQuery(query, Stream.class);
        return persistenceQuery.getResultList();
    }

}
