package com.arj.bankingapp.api;

import com.arj.bankingapp.entity.User;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang.RandomStringUtils;

@Path("user")
public class UserAPI extends GenericAPI{

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<User> index(){
        return userDAO.getAll();
    }
    
    @POST
    @Path("/signup")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public String signup(@FormParam("signup_username") String userName, @FormParam("signup_password") String password, @FormParam("signup_rePassword") String rePassword, @FormParam("signup_email") String email) {
        if(password.equals(rePassword)){
            User u = new User();
            u.setUserName(userName);
            u.setPassword(password);
            u.setEmail(email);
            u.setStatus(false);
            userDAO.insert(u);
            return "success";
        }
        return null;
    }

    @POST
    @Path("/login")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public String login(@FormParam("login_username") String userName, @FormParam("login_password") String password) {
        User user = userDAO.login(userName, password);
        if (user != null && user.isStatus()) {
            String accessToken = accessTokenGenerator();
            user.setAccessToken(accessToken);
            Date currentDateTime=new Date();
            Calendar cal= Calendar.getInstance();
            cal.setTime(currentDateTime);
            cal.add(Calendar.MINUTE, 60);
            Date tokenExpiryDate=cal.getTime();
            user.setSignedInAt(currentDateTime);
            user.setTokenExpiryDate(tokenExpiryDate);
            userDAO.update(user);
            return accessToken;
        }
        return "error";
    }

    private String accessTokenGenerator() {
        String token = RandomStringUtils.random(4, true, true)
                + "-" + RandomStringUtils.random(4, true, true) 
                + "-" + RandomStringUtils.random(4, true, true);
        return token.toUpperCase();
    }

    @GET
    @Path("/logout")
    @Consumes(value = MediaType.TEXT_PLAIN)
    public String logout(@QueryParam("accessToken")String accessToken) {
        User user = userDAO.getByAccessToken(accessToken);
        if (user != null) {
            user.setAccessToken(null);
            user.setSignedInAt(null);
            user.setTokenExpiryDate(null);
            userDAO.update(user);
            return "loggedout";
        }
        return "error";
    }
}
