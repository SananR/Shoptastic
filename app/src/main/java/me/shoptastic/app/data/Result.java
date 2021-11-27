package me.shoptastic.app.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
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

    // Success sub-class
    public final static class Success<T> extends Result {
        private final T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends Result {
        private final Exception exception;
        private Integer error;

        public Error(Exception error) {
            this.exception = error;
        }

        public Error(Exception exception, Integer error) {
            this.exception = exception;
            this.error = error;
        }

        public Exception getException() {
            return this.exception;
        }

        public Integer getError() {
            return this.error;
        }
    }
}