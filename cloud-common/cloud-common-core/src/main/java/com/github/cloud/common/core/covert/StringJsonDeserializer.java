package com.github.cloud.common.core.covert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.cloud.common.core.util.StrUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

/**
 * String 全局去空格 && 防止xss
 *
 * @author : czk
 * @date 2018-10-08
 */
public class StringJsonDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        return StringEscapeUtils.escapeHtml4(StrUtils.trim(p.getText()));
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }

}
