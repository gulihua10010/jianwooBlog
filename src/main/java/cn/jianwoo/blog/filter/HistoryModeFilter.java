package cn.jianwoo.blog.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gulihua
 * @Description
 * @date 2022-10-22 16:13
 */
@Component
@Slf4j
public class HistoryModeFilter extends OncePerRequestFilter {
    private String endpoint = "/";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(">>Vue url filter: [{}]", request.getRequestURI());
        RequestDispatcher rd = request.getRequestDispatcher(endpoint);
        rd.forward(request, response);
    }
}
