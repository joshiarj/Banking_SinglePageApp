package com.arj.bankingapp.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-26T21:50:53")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Date> tokenExpiryDate;
    public static volatile SingularAttribute<User, Date> addedDate;
    public static volatile SingularAttribute<User, Date> signedInAt;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> accessToken;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Boolean> status;

}