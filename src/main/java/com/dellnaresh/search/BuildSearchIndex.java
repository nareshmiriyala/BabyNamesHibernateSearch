package com.dellnaresh.search;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The only meaning for this class is to build the Lucene index at application
 * startup. This is needed in this example because the database is filled 
 * before and each time the web application is started. In a normal web 
 * application probably you don't need to do this.
 * 
 * @author netgloo
 */
@Component
public class BuildSearchIndex implements SearchIndex {
  
  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @PersistenceContext
  private EntityManager entityManager;

  private Logger logger= LoggerFactory.getLogger(BuildSearchIndex.class);

  public void index() {
    try {
      FullTextEntityManager fullTextEntityManager =
          Search.getFullTextEntityManager(entityManager);
      fullTextEntityManager.createIndexer().startAndWait();
    }
    catch (InterruptedException e) {
      logger.error(
          "An error occurred trying to build the serach index:  {}",
          e.toString());
    }
    return;
  }


} // class BuildSearchIndex

