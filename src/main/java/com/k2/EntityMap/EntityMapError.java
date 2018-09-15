package com.k2.EntityMap;

import com.k2.Util.StringUtil;

/**
 * This Error class is the root of all unchecked errors defined by the k2 utilities
 * 
 * @author simon
 *
 */
public class EntityMapError extends Error {

	private static final long serialVersionUID = 4832349980736372827L;

	/**
	 * Create an adapter error with the given message
	 * @param message	The error message
	 * @param replacements The objects to convert to strings to replace instances of '{}' in the message
	 */
	public EntityMapError(String message, Object ... replacements) {
		super(StringUtil.replaceAll(message, "{}", replacements));
	}
	/**
	 * Create an adapter error for the given throwable cause
	 * @param cause	The throwable that gave rise to this error
	 */
	public EntityMapError(Throwable cause) {
		super(cause);
	}
	/**
	 * Create an adapter error for the given message and throwable cause
	 * @param message	The error message
	 * @param cause	The throwable cause
	 * @param replacements The objects to convert to strings to replace instances of '{}' in the message
	 */
	public EntityMapError(String message, Throwable cause, Object ... replacements) {
		super(StringUtil.replaceAll(message, "{}", replacements), cause);
	}
	/**
	 * Create an adapter error for the given message and throwable cause defining whether the error can be suppressed and
	 * Whether the stack trace should be writable. 
	 * @param message	The error message
	 * @param cause	The throwable cause
	 * @param enableSuppression	True if the error can be suppressed
	 * @param writableStackTrace	True if the stack trace should be writable
	 * @param replacements The objects to convert to strings to replace instances of '{}' in the message
	 */
	public EntityMapError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object ... replacements) {
		super(StringUtil.replaceAll(message, "{}", replacements), cause, enableSuppression, writableStackTrace);
	}

}
