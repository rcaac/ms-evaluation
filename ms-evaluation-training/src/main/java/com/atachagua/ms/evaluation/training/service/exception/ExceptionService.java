package com.atachagua.ms.evaluation.training.service.exception;

public class ExceptionService extends Exception {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299624631424862749L;

	public ExceptionService() {

    }

    public ExceptionService(String message) {
        super(message);
    }

    public ExceptionService(Throwable cause) {
        super(cause);
    }

    public ExceptionService(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionService(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
