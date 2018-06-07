package com.application.library.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //do some logic here if you want something to be done whenever
        //the user successfully logs in.
 
        HttpSession session = request.getSession();
        /*Set some session variables*/
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);
 
        /*Set target URL to redirect*/
		String targetUrl = determineTargetUrl(authentication); 
        redirectStrategy.sendRedirect(request, response, targetUrl);
        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        //response.sendRedirect("home");
    }
    
    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        //Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
        System.out.println(authorities);
        
        if (authorities.contains("ROLE_USER")) {
        	isUser = true;
        } else if (authorities.contains("ROLE_ADMIN")) {
        	isAdmin = true;
        } else {
            throw new IllegalStateException();
        }
        
       /* for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }*/
 
        if (isUser) {
            return "/user/home";
        } else if (isAdmin) {
            return "/admin/home";
        } else {
            throw new IllegalStateException();
        }
    }
}
