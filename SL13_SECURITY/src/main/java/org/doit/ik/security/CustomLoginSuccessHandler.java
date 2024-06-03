package org.doit.ik.security;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("customLoginSuccessHandler")
@Log4j
public class CustomLoginSuccessHandler 
implements AuthenticationSuccessHandler{

   @Override
   public void onAuthenticationSuccess(
         HttpServletRequest request
         , HttpServletResponse response
         , Authentication authentication) // 인증받은 사용자의 정보 
               throws IOException, ServletException {
      log.warn("> Login Success...");
      // 권한이 여러개일수있으니까 처리해줌
      List<String> roleNames = new ArrayList<String>();      
      authentication.getAuthorities().forEach( auth -> {
         roleNames.add(auth.getAuthority());
      });
      log.warn("> ROLE NAMES : " + roleNames );
      if ( roleNames.contains("ROLE_ADMIN") ) {
         response.sendRedirect("/");
         return;
      } else if ( roleNames.contains("ROLE_MANAGER") ) {
         response.sendRedirect("/customer/notice.htm");
         return;
      } else if ( roleNames.contains("ROLE_USER") ) {
         response.sendRedirect("/customer/notice.htm");
         return;
      }
   }

}
