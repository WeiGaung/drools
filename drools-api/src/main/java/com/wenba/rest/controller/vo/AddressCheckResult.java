package com.wenba.rest.controller.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCheckResult {

    private boolean postCodeResult = false; // true:通过校验；false：未通过校验
}
