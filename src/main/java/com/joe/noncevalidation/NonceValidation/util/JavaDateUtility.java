package com.joe.noncevalidation.NonceValidation.util;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JavaDateUtility implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 2409138686574288009L;

    /**
     * Increment / decrement passed date by days / minutes passed
     * @param dateBegin
     * @param incrementDays
     * @param incrementMinutes
     * @return Date
     */
    public Date generateExpirationDate(Date dateBegin, int incrementDays, int incrementMinutes)
    {
        if (dateBegin == null)
        {
            return null;
        }

        Calendar expire = Calendar.getInstance();
        expire.setTime(dateBegin);
        if (incrementDays != 0)
        {
            expire.add(Calendar.DATE,incrementDays);
        }
        if (incrementMinutes != 0)
        {
            expire.add(Calendar.MINUTE,incrementMinutes);
        }
        return expire.getTime();
    }

}
