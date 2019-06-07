package com.cy.http;//package com.cy.sdkstrategy_master.http;


/**
 * Created by Administrator on 2018/12/21 0021.
 */

public class PostRequestGenerator extends BaseRequestGenerator<PostRequestGenerator> {
    protected byte[] byteProto;

    /**
     * protobuf请求时使用
     * @param bytes
     * @return
     */
    public PostRequestGenerator byteProto(byte[] bytes) {
        this.byteProto = bytes;
        return this;
    }

    @Override
    public Request generateRequest(Object tag) {
        Request.Builder builder = new Request.Builder();
        return builder.setTag(tag)
                .setUrl(url)
                .setHeader(header)
                .setMethod(method)
                .setParams(params)
                .setByteProto(byteProto)
                .build();

    }
}
