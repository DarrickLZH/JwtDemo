package com.uchain.jwtlogindemo.util;

import com.uchain.jwtlogindemo.Enum.ResultEnum;
import com.uchain.jwtlogindemo.vo.ResultVO;
import lombok.Data;

/**
 * @ClassName ResultVOUtil
 * @Author hobo
 * @Date 19-4-22 下午3:40
 * @Description
 **/
@Data
public class ResultVOUtil {

    /**
     * @param object
     * @return 有参成功结果
     * @author hobo
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    /***
     *
     * 无参成功结果
     *
     * @author hobo
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 返回错误结果
     *
     * @param resultEnum
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
