package doan.springmvcexceptionhandling.demo.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import doan.springmvcexceptionhandling.demo.exception.ExceptionVO;
import doan.springmvcexceptionhandling.demo.exception.FunctionalException;

@Controller
public class HomeController
{

	@RequestMapping(value =
	{
			"/",
			"/home"
	}, method = RequestMethod.GET)
	public String home()
	{
		return "pages/home";
	}

	/********** Exception 1 ***************/
	@RequestMapping(value = "rest/exception1")
	public String exception1()
	{
		throw new NullPointerException("Exception1 as plain text with <strong>html</strong> tags");
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public String handleException1(NullPointerException ex)
	{
		return ex.getMessage();
	}

	/********** Exception 2 ***************/
	@RequestMapping(value = "http/exception2", method = RequestMethod.GET)
	public String exception2()
	{
		throw new IndexOutOfBoundsException();
	}

	@ExceptionHandler(IndexOutOfBoundsException.class)
	public String handleException2(IndexOutOfBoundsException ex)
	{
		return "pages/errorPage";
	}

	/********** Exception 3 ***************/
	@RequestMapping(value = "http/exception3", method = RequestMethod.GET)
	public String exception3()
	{
		throw new IllegalStateException("Exception3 with response status");
	}

	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "exception3")
	public ModelAndView handleException3(IllegalStateException ex, HttpServletResponse response) throws IOException
	{
		return new ModelAndView();
	}

	/********** Exception 4 ***************/
	@RequestMapping(value = "http/exception4", method = RequestMethod.GET)
	public String exception4() throws FunctionalException
	{
		throw new FunctionalException("Functional exception");
	}

	@ExceptionHandler(FunctionalException.class)
	public RedirectView handleException4(FunctionalException ex, HttpServletRequest request) throws IOException
	{
		RedirectView redirectView = new RedirectView("../errorRedirectPage");
		redirectView.addStaticAttribute("errorMessage", ex.getMessage());
		return redirectView;
	}

	@RequestMapping(value = "errorRedirectPage")
	public String errorRedirectPage(HttpServletRequest request, Model model, @RequestParam("errorMessage") String errorMessage)
	{
		model.addAttribute("errorMessage", errorMessage);
		return "pages/errorRedirectPage";
	}

	/********** Exception 5 ***************/
	@RequestMapping(value = "rest/exception5", method = RequestMethod.GET)
	public String exception5(HttpSession session)
	{
		throw new IllegalArgumentException("Test exception 5 with ExceptionVO as JSON data");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionVO handleException5(IllegalArgumentException ex, HttpServletResponse response) throws IOException
	{
		ExceptionVO exceptionVO = new ExceptionVO("handleException5()", "HomeController", ex.getMessage());

		return exceptionVO;

	}

	/********** Exception 6 ***************/
	@RequestMapping(value = "http/exception6", method = RequestMethod.GET)
	public String exception6() throws HttpMediaTypeNotSupportedException
	{
		throw new HttpMediaTypeNotSupportedException("Test for DefaultHandlerExceptionResolver");
	}

	/********** Exception 7 ***************/
	@RequestMapping(value = "http/exception7", method = RequestMethod.GET)
	public String exception7() throws ClassNotFoundException
	{
		throw new ClassNotFoundException("Test for SimpleMappingExceptionResolver");
	}

	/********** Exception 8 ***************/
	@RequestMapping(value = "http/exception8", method = RequestMethod.GET)
	public String exception8() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException("Test for SimpleMappingExceptionResolver");
	}

}
