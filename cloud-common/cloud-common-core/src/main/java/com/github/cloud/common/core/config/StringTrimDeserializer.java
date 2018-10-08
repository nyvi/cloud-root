package com.github.cloud.common.core.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.cloud.common.core.util.StrUtils;

import java.io.IOException;

/**
 * String 全局去空格
 *
 * @author : czk
 * @date 2018-10-08
 */
public class StringTrimDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        return StrUtils.trim(p.getValueAsString());
    }

}
