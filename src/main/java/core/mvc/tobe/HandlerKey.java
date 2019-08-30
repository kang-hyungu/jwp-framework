package core.mvc.tobe;

import core.annotation.web.RequestMethod;

import java.util.Objects;

public class HandlerKey {
    private String url;
    private RequestMethod requestMethod;

    public HandlerKey(String url, RequestMethod requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "HandlerKey [url=" + url + ", requestMethod=" + requestMethod + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HandlerKey)) return false;
        HandlerKey that = (HandlerKey) o;
        return Objects.equals(url, that.url) &&
                requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, requestMethod);
    }
}
