package com.mengya.sms;

import com.mengya.sms.domain.RestResult;

public interface SmsService {
    RestResult.RestCode sendCode(String mobile, String code);
}
