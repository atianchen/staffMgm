package com.yonyou.stm.api.req.ocr;

/**
 * Created by atian on 2017/11/26.
 */

public class OcrInputs {

    private OcrImg image;

    private OcrConfig configure;

    public OcrImg getImage() {
        return image;
    }

    public void setImage(OcrImg image) {
        this.image = image;
    }

    public OcrConfig getConfigure() {
        return configure;
    }

    public void setConfigure(OcrConfig configure) {
        this.configure = configure;
    }
}
