package com.joe.noncevalidation.NonceValidation.scheduledtasks;

import com.joe.noncevalidation.NonceValidation.service.NonceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteNonce implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 744443163229829488L;


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NonceService nonceService;

    @Scheduled(cron = "0 0 */1 * * *")
    public void run()
    {
        log.info("start delete expired nonce ");
        nonceService.deleteExpired();
        log.info("end delete expired nonce");
    }

}
