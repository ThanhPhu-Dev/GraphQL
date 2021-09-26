package dinhthanhphu.graphql.repository;

import dinhthanhphu.graphql.dto.Author;
import dinhthanhphu.graphql.dto.Book;
import dinhthanhphu.graphql.entity.AuthorEntity;
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
public class AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    public List<AuthorEntity> getAuthors(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<AuthorEntity> query = builder.createQuery(AuthorEntity.class);
        Root<AuthorEntity> root = query.from(AuthorEntity.class);
        return em.createQuery(query.select(root)).getResultList();
    }

    public AuthorEntity getAuthor(Long id){
        CriteriaBuilder builder  = em.getCriteriaBuilder();
        CriteriaQuery<AuthorEntity> query = builder.createQuery(AuthorEntity.class);
        Root<AuthorEntity> root = query.from(AuthorEntity.class);
        Predicate condition = builder.equal(root.get(AuthorEntity_.ID), id);
       return em.createQuery(query.select(root).where(condition)).getSingleResult();
    }

    @Transactional
    public AuthorEntity save(AuthorEntity author){
        try{
            if(author.getId() != null){
                em.merge(author);
            }else{
                em.persist(author);
            }
            return author;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void delete(Long id) throws Exception{
        AuthorEntity book = getAuthor(id);
        em.remove(book);
    }
}
