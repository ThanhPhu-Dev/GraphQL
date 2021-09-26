package dinhthanhphu.graphql.repository;

import dinhthanhphu.graphql.entity.AuthorEntity_;
import dinhthanhphu.graphql.entity.BookEntity;
import dinhthanhphu.graphql.entity.BookEntity_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookRepository {
    @PersistenceContext
    private EntityManager em;

    public List<BookEntity> getBooks() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BookEntity> query = builder.createQuery(BookEntity.class);
        Root<BookEntity> root = query.from(BookEntity.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    public BookEntity getBookById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BookEntity> query = builder.createQuery(BookEntity.class);
        Root<BookEntity> root = query.from(BookEntity.class);
        Predicate condition = builder.equal(root.get(BookEntity_.ID), id);
        return em.createQuery(query.select(root).where(condition)).getSingleResult();
    }

    public List<BookEntity> getBooksByAuthorId(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BookEntity> query = builder.createQuery(BookEntity.class);
        Root<BookEntity> bookEntity = query.from(BookEntity.class);
        Join<Object, Object> author = bookEntity.join(BookEntity_.AUTHOR);
        query.select(bookEntity).where(builder.equal(author.get(AuthorEntity_.ID), id));
        TypedQuery<BookEntity> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Transactional
    public BookEntity save(BookEntity book) {
        try {
            if (book.getId() != null) {
                em.merge(book);
            } else {
                em.persist(book);
            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void delete(Long id) throws Exception{
        BookEntity book = getBookById(id);
        em.remove(book);
    }
}
