package com.wenba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ Author     ：wenshuai.zhang
 * @ Date       ：Created in 5:43 PM 2018/10/24
 * @ Version    ：1.0
 * @ Modified By：
 * @ Description：基础运行时异常
 * 通常用于不可恢复的异常，如违反约定 参数异常
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRTException extends RuntimeException {
    private String msg;
    private Integer line;
    private Integer column;
}
