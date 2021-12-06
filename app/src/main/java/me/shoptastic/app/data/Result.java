package me.shoptastic.app.data;

import androidx.annotation.StringRes;

import me.shoptastic.app.data.model.Resources;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getException().toString() + "]";
        }
        return "";
    }

    /**
     * Success sub-class
     * Use this subclass when an operation has been successful to return the data
     * i.e. new Result.Success<User>(loggedInUser)
     **/
    public final static class Success<T> extends Result {
        private final T data;

        /**
         * Successful operation that returns data
         *
         * @param data Successful data
         */
        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    /**
     * Error sub-class
     * Use this subclass when an operation has failed to return an exception and an error msg
     * i.e. new Result.Error(new IllegalArgumentException("invalid password"), "has to be longer")
     */
    public final static class Error extends Result {
        private final Exception exception;
        private String error;

        /**
         * An error occurred with no error msg
         *
         * @param error Exception that occurred
         */
        public Error(Exception error) {
            this.exception = error;
        }

        /**
         * An error occurred with an error message provided as a resource
         *
         * @param exception Exception that occurred
         * @param error     String resource integer
         */
        public Error(Exception exception, @StringRes Integer error) {
            this(exception);
            this.error = Resources.getString(error);
        }

        /**
         * An error occurred with the specified error msg
         *
         * @param exception Exception that occurred
         * @param error     Error msg
         */
        public Error(Exception exception, String error) {
            this(exception);
            this.error = error;
        }

        public Exception getException() {
            return this.exception;
        }

        public String getError() {
            return this.error;
        }
    }
}