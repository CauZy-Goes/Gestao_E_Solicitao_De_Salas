package io.github.cauzy.GSDS.Utility.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/pages/gestor/*") // Protege todas as páginas dentro de "gestor/"
public class gestorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Verifica se o usuário está logado

        if (session == null || session.getAttribute("gestor") == null) {
            res.sendRedirect(req.getContextPath() + "/index.xhtml"); // Redireciona para login
            return;
        }

        chain.doFilter(request, response); // Continua o fluxo se estiver logado
    }
}
