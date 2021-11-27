package com.zitga.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zitga.ServiceController;
import com.zitga.base.constant.HttpMessage;
import com.zitga.base.model.message.ResponseMessage;
import com.zitga.core.message.http.HttpResponse;
import com.zitga.support.JsonService;
import com.zitga.utils.GZipUtils;

public class HttpResponseUtils {

    public static HttpResponse success(String content) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(HttpMessage.SUCCESS);
        responseMessage.setContent(content);

        JsonService jsonService = ServiceController.instance().getJsonService();
        String data = GZipUtils.compress(jsonService.writeValueAsString(responseMessage));
        return HttpResponse.ok(data);
    }
}
