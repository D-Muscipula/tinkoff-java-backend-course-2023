package edu.project3;

import java.time.OffsetDateTime;
import java.util.Objects;

public record LogRecord(String remoteAddress, String remoteUser, OffsetDateTime timeLocal,
                        String requestMethod, String requestUri, int status, int bodyBytesSent, String httpReferer,
                        String httpUserAgent) {
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogRecord logRecord = (LogRecord) o;
        return status == logRecord.status && bodyBytesSent == logRecord.bodyBytesSent
            && Objects.equals(remoteAddress, logRecord.remoteAddress)
            && Objects.equals(remoteUser, logRecord.remoteUser) && Objects.equals(timeLocal, logRecord.timeLocal)
            && Objects.equals(requestMethod, logRecord.requestMethod)
            && Objects.equals(requestUri, logRecord.requestUri)
            && Objects.equals(httpReferer, logRecord.httpReferer)
            && Objects.equals(httpUserAgent, logRecord.httpUserAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            remoteAddress,
            remoteUser,
            timeLocal,
            requestMethod,
            requestUri,
            status,
            bodyBytesSent,
            httpReferer,
            httpUserAgent
        );
    }
}
