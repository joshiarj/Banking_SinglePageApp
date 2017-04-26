package com.arj.bankingapp.api;

import com.arj.bankingapp.entity.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang.RandomStringUtils;

@Path("login")
public class LoginAPI extends GenericAPI {

    @POST
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public String login(@FormParam("login_username") String userName, @FormParam("login_password") String password) {
        User user = userDAO.login(userName, password);
        if (user != null && user.isStatus()) {
            String accessToken = accessTokenGenerator();
            user.setAccessToken(accessToken);
            userDAO.update(user);
            return accessToken;
        }
        return "error";
    }

    private String accessTokenGenerator() {
        String token = RandomStringUtils.random(4, true, true) + "-" + RandomStringUtils.random(4, true, true)
                + "-" + RandomStringUtils.random(4, true, true);
        return token;
    }
}
