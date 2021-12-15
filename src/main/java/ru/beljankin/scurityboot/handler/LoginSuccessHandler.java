package ru.beljankin.scurityboot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.beljankin.scurityboot.entities.User;
import ru.beljankin.scurityboot.services.UserServiceImplDS;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private UserServiceImplDS userService;

    @Autowired
    public void setUserService(UserServiceImplDS userService){
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
//        httpServletResponse.sendRedirect("/admin");
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else if (roles.contains("ROLE_USER") || roles.contains("ROLE_MANAGER")) {

            Principal principal = (Principal) authentication.getPrincipal();
            User user = userService.findByUsername(principal.getName());

            httpServletResponse.sendRedirect("/user/" + user.getId());
        }

    }
}