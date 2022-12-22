package dev.palmes.ilovejava.conf;

import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.model.User;
import dev.palmes.ilovejava.service.UserService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class WebFilter implements Filter {

    private final UserService userService;

    public WebFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if (!((HttpServletRequest) servletRequest).getRequestURI().startsWith("/static/")) {
            if (session.getAttribute("user") != null) {
                try {
                    session.setAttribute("user", userService.get(
                            ((User) session.getAttribute("user")).getId()
                    ));
                } catch (NotFoundException e) {
                    session.invalidate();
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
