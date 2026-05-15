package com.soc.ewok.controller.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.ewok.controller.EWokController;

/**
 * Servlet Filter implementation class FiltreIdioma
 */
public class FiltreIdioma implements Filter {
	
	public static final String PARAM_IDIOMA = "idioma";
	public static final String SESS_IDIOMA = "idioma";
	public static final String COOKIE_LASTIDIOMA = "lastIdioma";
	public static final int COOKIE_LASTIDIOMA_AGE = 30 * 24 * 60 * 60;	// 30 dies
	public static final String PARAM_IDIOMA_CASTELLA = "es";
	public static final String PARAM_IDIOMA_CATALA = "ca";
	public static final String PARAM_IDIOMA_ITALIA = "it";
	public static final String PARAM_IDIOMA_ANGLES = "en";
	public static final String idiomes[] = 
			new String[] {PARAM_IDIOMA_CASTELLA, PARAM_IDIOMA_CATALA, PARAM_IDIOMA_ITALIA,
									PARAM_IDIOMA_ANGLES};
	public static final String IDIOMA_DEFECTE = PARAM_IDIOMA_CATALA;
    /**
     * Default constructor. 
     */
    public FiltreIdioma() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Posem el request com utf-8
		request.setCharacterEncoding("utf-8");
		
		String sIdioma;
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		// Mirem si demanen canviar d'idioma
		sIdioma = request.getParameter(PARAM_IDIOMA);
		if (isIdiomaValid(sIdioma)) {
			EWokController.setIdioma(sIdioma, request);
			
			// Enviem cookie amb la preferència de l'usuari
			Cookie cookieIdioma = new Cookie(COOKIE_LASTIDIOMA, sIdioma);
			cookieIdioma.setMaxAge(COOKIE_LASTIDIOMA_AGE);
			httpResp.addCookie(cookieIdioma);
			
			// pass the request along the filter chain
			chain.doFilter(request, response);
			return;
		}
		
		if (EWokController.getIdioma(request) == null) {
			
			// Mirem si ja tenim un idioma a la cookie
			Cookie[] cookies = httpReq.getCookies();
			if (cookies != null) {
				for(Cookie cookie : cookies) {
					if (COOKIE_LASTIDIOMA.equals(cookie.getName())) {
						sIdioma = cookie.getValue();
						if (isIdiomaValid(sIdioma)) {
							EWokController.setIdioma(sIdioma, request);
							// pass the request along the filter chain
							chain.doFilter(request, response);
							return;
						}
					}
				}
			}
		
			// Si no hi ha idioma a la cookie, posarem una de les preferencies del navegador
			Enumeration en = request.getLocales();
			while (en.hasMoreElements()) {
				Locale loc = (Locale)en.nextElement();
				sIdioma = loc.getLanguage();
				if (isIdiomaValid(sIdioma)) {
					EWokController.setIdioma(sIdioma, request);
					// pass the request along the filter chain
					chain.doFilter(request, response);
					return;
				}
			}
			
			// Si tampoc es podem posar les preferencies del navegador, posem un llenguatge per defecte
			EWokController.setIdioma(IDIOMA_DEFECTE, request);
			// pass the request along the filter chain
			chain.doFilter(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public boolean isIdiomaValid(String idioma) {
		for (String idiomaLlistat : idiomes) {
			if (idiomaLlistat.equals(idioma))
				return true;
		}
		return false;
	}
	
}
