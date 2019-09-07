package com.uchain.jwtlogindemo.vo;

import lombok.Data;

/**
 * @author；lzh
 * @Date:2019/8/1319:14 Descirption:
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
