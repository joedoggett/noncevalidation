package com.joe.noncevalidation.NonceValidation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries
({
        @NamedQuery
                (name="getNonceByNonceUserNameNotExpired",
                        query="SELECT n FROM Nonce n WHERE n.nonce = :nonce AND n.userName = :userName AND n.expiration > :date"
                ),
        @NamedQuery
                (name="deleteExpired",
                        query="DELETE FROM Nonce n WHERE n.expiration < :date"),
        @NamedQuery
                (name="deleteNonceByNonce",
                        query="DELETE FROM Nonce n WHERE n.nonce = :nonce")

})

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "nonce"))
public class Nonce implements java.io.Serializable
{



    /**
     *
     */
    private static final long serialVersionUID = 6701735162119127949L;

    private String nonce;
    private Date expiration;
    private String userName;
    private int userId;

    @Id
    @Size(min = 128, max = 128)
    public String getNonce()
    {
        return nonce;
    }

    public void setNonce(String nonce)
    {
        this.nonce = nonce;
    }

    public Date getExpiration()
    {
        return expiration;
    }
    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }



    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
        result = prime * result + ((nonce == null) ? 0 : nonce.hashCode());
        result = prime * result + userId;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nonce other = (Nonce) obj;
        if (expiration == null) {
            if (other.expiration != null)
                return false;
        } else if (!expiration.equals(other.expiration))
            return false;
        if (nonce == null) {
            if (other.nonce != null)
                return false;
        } else if (!nonce.equals(other.nonce))
            return false;
        if (userId != other.userId)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }






}
