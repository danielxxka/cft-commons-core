package cft.commons.core.web.intereptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author daniel
 *
 */
public class WebStopWatchInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebStopWatchInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		StopWatch stopWatch = new Slf4JStopWatch(request.getRequestURI());
		request.setAttribute("stopWatch", stopWatch);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		StopWatch stopWatch = (StopWatch) request.getAttribute("stopWatch");
		stopWatch.stop();

		logger.info("WebStopWatchInterceptor: URI = {}, handlingTime = {} ms",
				new Object[] { request.getRequestURI(), stopWatch.getElapsedTime() });

	}
}