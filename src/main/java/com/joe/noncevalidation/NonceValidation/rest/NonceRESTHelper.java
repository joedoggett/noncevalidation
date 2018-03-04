package com.joe.noncevalidation.NonceValidation.rest;

import com.joe.noncevalidation.NonceValidation.model.Nonce;
import com.joe.noncevalidation.NonceValidation.model.NoncePass;
import com.joe.noncevalidation.NonceValidation.service.NonceService;
import com.joe.noncevalidation.NonceValidation.util.JavaDateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NonceRESTHelper implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = -1567249113507121454L;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NonceService nonceService;

    @Autowired
    JavaDateUtility javaDateUtility;

    public NoncePass insertNonce(NoncePass inNoncePass)
    {
        Nonce nonce = new Nonce();
        nonce.setNonce(inNoncePass.getNonce());
        nonce.setUserId(inNoncePass.getUserId());
        nonce.setUserName(inNoncePass.getUserName());
        nonce.setExpiration(javaDateUtility.generateExpirationDate(new Date(), 0, 30));
        nonceService.insertNonce(nonce);

        NoncePass noncePass = new NoncePass();
        noncePass.setSuccess(true);
        return noncePass;
    }

    /**
     * validate nonce.
     * fail fast.
     * check to see if have nonce.
     * check to see if userName passed matches userName held.
     *
     * @param inNoncePass
     * @return
     */
    public NoncePass validateNonce(NoncePass inNoncePass)
    {
        NoncePass outNoncePass = new NoncePass();
        outNoncePass.setSuccess(false);
        Nonce nonce = nonceService.getNonceByIdUserName(inNoncePass.getNonce(), inNoncePass.getUserName());

        try
        {
            if(nonce.getNonce() == null )
            {
                return outNoncePass;
            }


            outNoncePass.setSuccess(true);
            outNoncePass.setUserName(nonce.getUserName());
            outNoncePass.setUserId(nonce.getUserId());

            return outNoncePass;
        }
        catch(Exception e)
        {
            log.error("error validating nonce.", e);
            return outNoncePass;
        }
        finally
        {
            nonce.setNonce(inNoncePass.getNonce());
            try
            {
                nonceService.deleteNonce(nonce);
            }
            catch(Exception e)
            {
                log.error("error trying to delete nonce" + nonce.getNonce(), e);
            }
        }
    }

    public void deleteExpired()
    {
        nonceService.deleteExpired();
    }

}
