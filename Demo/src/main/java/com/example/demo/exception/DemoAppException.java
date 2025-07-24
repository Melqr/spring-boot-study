package com.example.demo.exception;

public class DemoAppException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new DemoAppException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     * by the {@link #getMessage()} method).
     */
    public DemoAppException(String message) {
        super(message);
    }

    /**
     * Constructs a new DemoAppException with the specified detail message and
     * cause.
     * <p>
     * Note that the cause is not automatically incorporated in the detail
     * message of this exception.
     *
     * @param message the detail message (which is saved for later retrieval
     * by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A null value is permitted, and
     * indicates that the cause is nonexistent or unknown.)
     */
    public DemoAppException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new DemoAppException with the specified cause and a detail
     * message of {@code (cause==null ? "" : cause.toString())} (which typically
     * contains the class and detail message of cause).  This constructor is
     * useful for exceptions that are little more than wrappers for other
     * throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A null value is permitted, and
     * indicates that the cause is nonexistent or unknown.)
     */
    public DemoAppException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new DemoAppException with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack trace enabled
     * or disabled.
     *
     * @param message the detail message.
     * @param cause the cause.  (A {@code null} value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     *
     * @since 1.7
     */
    protected DemoAppException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

