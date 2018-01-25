package com.bluefish.seckill.exception;

import com.bluefish.seckill.result.CodeMsg;

/**
 * 全局异常类
 *
 * @author bluefish 2018/1/20
 * @version 1.0.0
 */
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = -6341190161561452318L;

    private CodeMsg cm;

    public CodeMsg getCm() {
        return cm;
    }

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }
}

