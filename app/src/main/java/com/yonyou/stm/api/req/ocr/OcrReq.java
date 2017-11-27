package com.yonyou.stm.api.req.ocr;

/**
 * Created by atian on 2017/11/26.
 */

public class OcrReq {

    private OcrInputs inputs;

    public OcrReq(OcrInputs arg)
    {
        this.inputs = arg;
    }

    public OcrInputs getInputs() {
        return inputs;
    }

    public void setInputs(OcrInputs inputs) {
        this.inputs = inputs;
    }
}
