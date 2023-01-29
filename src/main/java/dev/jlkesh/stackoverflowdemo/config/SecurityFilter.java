package dev.jlkesh.stackoverflowdemo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;


@Component
public class SecurityFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class.getSimpleName());


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        LOGGER.info("Starting a transaction for req : " + req.getRequestURI());
        filterChain.doFilter(request, response);
        LOGGER.info("Committing a transaction for req :  " + req.getRequestURI());
    }

}
