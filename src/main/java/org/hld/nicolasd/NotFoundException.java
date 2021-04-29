package org.hld.nicolasd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 4936006127238912453L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

}
