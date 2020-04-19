package com.zcf.exception;

import com.zcf.utils.ResultEnum;
import lombok.Data;

/**
 * @author zhangchuanfei
 * @create 2020-04-19-10:22
 */
@Data
public class CustomException extends RuntimeException {

    private Integer code;

    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getInfo());
        this.code = resultEnum.getCode();
    }

    public CustomException(Integer code , String info) {
        super(info);
        this.code = code;
    }

}
