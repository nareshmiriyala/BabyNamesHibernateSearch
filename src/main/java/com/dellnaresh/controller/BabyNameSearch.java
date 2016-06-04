package com.dellnaresh.controller;

import com.dellnaresh.model.BabyName;
import org.apache.lucene.queryParser.QueryParser;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nmiriyal on 4/06/2016.
 */
@Transactional
@Component
public class BabyNameSearch implements NameSearch {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BabyName> search(String text) {

        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                        getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(BabyName.class).get();

        // a very basic query by keywords
        org.apache.lucene.search.Query query =
                queryBuilder
                        .keyword().fuzzy()
                        .onFields("name", "year", "gender","count")
                        .matching(text)
                        .createQuery();

        // wrap Lucene query in an Hibernate Query object
        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, BabyName.class);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<BabyName> results = jpaQuery.getResultList();

        return results;
    } // method search
}
