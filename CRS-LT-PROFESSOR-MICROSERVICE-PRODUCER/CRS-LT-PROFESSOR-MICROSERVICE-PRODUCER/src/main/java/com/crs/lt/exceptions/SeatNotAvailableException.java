package com.crs.lt.exceptions;
/**
 * 
 * @author user215
 *
 */
public class SeatNotAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseCode;

	public SeatNotAvailableException(String courseCode)
	{	
		this.courseCode = courseCode;
	}

	@Override
	public String getMessage() {
		return  "Seats are not available in : " + courseCode;
	}

}
