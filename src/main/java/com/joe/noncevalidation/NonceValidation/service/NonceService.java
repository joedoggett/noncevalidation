package com.joe.noncevalidation.NonceValidation.service;

import java.util.List;


import com.joe.noncevalidation.NonceValidation.dao.NonceDAO;

import com.joe.noncevalidation.NonceValidation.model.Nonce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NonceService implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 8059794819101268399L;


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NonceDAO nonceDAO;



    @Async
    public void insertNonce(Nonce nonce)
    {
        nonceDAO.insertNonce(nonce);
    }

    @Async
    public void deleteNonce(Nonce nonce)
    {
        nonceDAO.deleteNonce(nonce);
    }

    @Async
    public void deleteExpired()
    {
        try
        {
            nonceDAO.deleteExpired();
        }
        catch(Exception e)
        {
            log.error("error trying to delete expired", e);
        }

    }

    public Nonce getNonceByIdUserName(String id, String userName)
    {
        return this.getNonce(id, userName);
    }

    private Nonce getNonce(String id, String userName)
    {
        try
        {
            List<Nonce> nonces = nonceDAO.getNonceByIdUserNameAndNotExpired(id, userName);
            if(!nonces.isEmpty())
            {
                return nonces.get(0);
            }

        }
        catch(Exception e)
        {
            log.error("error getting nonce: " , e);
        }
        return new Nonce();
    }

}
