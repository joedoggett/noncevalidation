package com.joe.noncevalidation.NonceValidation.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import com.joe.noncevalidation.NonceValidation.model.Nonce;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class NonceDAO implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 8688965766105348105L;
    private static final String GET_NONCE_BY_NONCE_USER_NAME_NOT_EXPIRED = "getNonceByNonceUserNameNotExpired";
    private static final String DELETE_EXPIRED = "deleteExpired";
    private static final String DELETE_NONCE_BY_NONCE = "deleteNonceByNonce";
    private static final String NONCE = "nonce";
    private static final String USER_NAME = "userName";
    private static final String DATE= "date";

    @PersistenceContext
    EntityManager entityManager;

    public void insertNonce(Nonce nonce)
    {
        entityManager.persist(nonce);
    }

    public void deleteNonce(Nonce nonce)
    {
        entityManager.createNamedQuery(DELETE_NONCE_BY_NONCE)
                .setParameter(NONCE, nonce.getNonce())
                .executeUpdate();
    }

    public List<Nonce> getNonceByIdUserNameAndNotExpired(String nonce, String userName)
    {
        return entityManager.createNamedQuery(GET_NONCE_BY_NONCE_USER_NAME_NOT_EXPIRED)
                .setParameter(NONCE, nonce)
                .setParameter(USER_NAME, userName)
                .setParameter(DATE, new Date())
                .getResultList();
    }

    public void deleteExpired()
    {
        entityManager.createNamedQuery(DELETE_EXPIRED)
                .setParameter(DATE, new Date())
                .executeUpdate();
    }

}
