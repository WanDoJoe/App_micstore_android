package com.wdq.micorestore.httpapi;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by sinosoft_wan on 2017/10/31.
 */

public class UploadFileRequestBody extends RequestBody {
    private RequestBody mRequestBody;
    private BufferedSink bufferedSink;
    private String url;

    public UploadFileRequestBody(File file, String mimeType, String url) {
        // this.mRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        this.mRequestBody = RequestBody.create(MediaType.parse(mimeType), file);
        this.url = url;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
