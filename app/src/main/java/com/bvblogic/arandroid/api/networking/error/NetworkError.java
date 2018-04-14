package com.bvblogic.arandroid.api.networking.error;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * Created by hanz on 14.04.2018.
 */

public class NetworkError extends Throwable {


    private final Throwable error;

    public NetworkError(Throwable error) {
        super(error);
        this.error = error;
    }

    public String getMessage() {
        return error.getMessage();
    }

    public String getAppErrorMessage() {
        if (this.error instanceof IOException) return "not inet connect";
        if ((this.error instanceof HttpException)) return "Error";
        return "base error";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        NetworkError networkError = (NetworkError) obj;

        return error != null ? error.equals(networkError.error)
                : networkError.error == null;
    }


    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
