package com.joe.noncevalidation.NonceValidation.rest;

import com.joe.noncevalidation.NonceValidation.model.NoncePass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NonceController
{

    /**
     *
     */
    private static final long serialVersionUID = 6704248929480952800L;

    @Value("${insert.nonce.pass.phrase}")
    private String INSERT_NONCE_PASS_PHRASE;

    @Value("${validate.nonce.pass.phrase}")
    private String VALIDATE_NONCE_PASS_PHRASE;

    @Autowired
    NonceRESTHelper nonceHelper;

    private Logger log = LoggerFactory.getLogger(this.getClass());



    @RequestMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> insertNonce(@RequestBody NoncePass noncePass)
    {
        if(!noncePass.getPassPhrase().equals(INSERT_NONCE_PASS_PHRASE))
        {
            log.info("insert nonce pass does not equal.");
            return ResponseEntity.ok().body("");
        }
        try
        {
            return ResponseEntity.ok().body(nonceHelper.insertNonce(noncePass));
        }
        catch(Exception e)
        {
            log.error("error inserting nonce.", e);
            return ResponseEntity.badRequest().body("");
        }
    }

    @RequestMapping(path = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> validateNonce(@RequestBody NoncePass noncePass)
    {

        if(!noncePass.getPassPhrase().equals(VALIDATE_NONCE_PASS_PHRASE))
        {
            log.info("nonce pass does not equal.");
            return ResponseEntity.ok().body("");
        }
        try
        {
            return ResponseEntity.ok().body(nonceHelper.validateNonce(noncePass));
        }
        catch(Exception e)
        {
            log.error("error validating nonce.", e);
            return ResponseEntity.badRequest().body("");
        }
    }

}
