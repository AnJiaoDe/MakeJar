package com.cy.http;

import java.util.Map;

/**
 * Created by Administrator on 2018/12/21 0021.
 */

public class Request {

    private Object tag;
    private String url;
    private String method;
    private Map<String, String> headers;
    private Map<String, Object> params;
    private byte[] byteProto;

    Request(Builder builder) {
        this.tag = builder.getTag();
        this.url = builder.getUrl();
        this.method = builder.getMethod();
        this.headers=builder.getHeader();
        this.params=builder.getParams();
        this.byteProto=builder.getByteProto();

    }

    public static class Builder {
        private Object tag;
        private String url;
        private String method;
        private Map<String, String> header;
        private Map<String, Object> params;
        private byte[] byteProto;

        public Builder() {
            this.method = "GET";
            this.tag = "tag";

        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder setHeader(Map<String, String> header) {
            this.header = header;
            return this;
        }
        public Builder setParams(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public Builder setTag(Object tag) {
            if (tag != null) this.tag = tag;
            return this;
        }

        public Builder setByteProto(byte[] byteProto) {
            this.byteProto = byteProto;
            return this;
        }

        public Request build() {
            return new Request(this);
        }

        public Object getTag() {
            return tag;
        }

        public String getUrl() {
            return url;
        }

        public String getMethod() {
            return method;
        }

        public Map<String, String> getHeader() {
            return header;
        }

        public Map<String, Object> getParams() {
            return params;
        }

        public byte[] getByteProto() {
            return byteProto;
        }
    }

    public Object getTag() {
        return tag;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public byte[] getByteProto() {
        return byteProto;
    }

    public Map<String, String> getHeader() {
        return headers;
    }


    public Map<String, Object> getParams() {
        return params;
    }
}
