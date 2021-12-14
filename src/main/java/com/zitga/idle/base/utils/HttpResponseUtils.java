package com.zitga.idle.base.utils;

import com.zitga.core.message.http.HttpResponse;
import com.zitga.idle.ServiceController;
import com.zitga.idle.base.constant.HttpMessage;
import com.zitga.idle.base.model.message.ResponseMessage;
import com.zitga.idle.utils.GZipUtils;
import com.zitga.support.JsonService;

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
