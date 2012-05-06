package doan.springmvcexceptionhandling.demo.exception;

public class ExceptionVO
{

	private String method;
	private String clazz;
	private String message;

	public ExceptionVO(String method, String clazz, String message) {
		super();
		this.method = method;
		this.clazz = clazz;
		this.message = message;
	}

	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public String getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz)
	{
		this.clazz = clazz;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
