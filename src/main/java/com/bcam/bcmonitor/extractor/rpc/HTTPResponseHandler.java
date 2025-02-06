package com.bcam.bcmonitor.extractor.rpc;

import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.HttpResponseException;


import java.io.IOException;


/**
 * Takes HTTP response, handles errors and returns an InputStream
 *
 * Notes:
 * Handleresponse must return value of type ResponseHandler<T>
 *
 */
public class HTTPResponseHandler implements HttpClientResponseHandler<String> {

    @Override
    public String handleResponse(HttpResponse response) throws IOException {

        // TODO add better error handling
        StatusLine statusLine = new StatusLine(response);
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }

        return EntityUtils.toString(entity);

    }
}
