package com.wenba.domain;

/**
 * Created by imiiot on 2017/6/1.
 */
public class ResultUtil {
    public static Result success() {
        Result result = new Result();
        result.setCode(1);
        result.setMessage("success");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage("fail");
        return result;
    }

}
