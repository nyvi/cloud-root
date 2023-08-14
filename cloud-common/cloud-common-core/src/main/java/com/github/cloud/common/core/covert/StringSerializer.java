package com.github.cloud.common.core.covert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

/**
 * json 序列化反转义
 *
 * @author : huweihua
 * @date 2023-08-30
 */
public class StringSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(StringEscapeUtils.unescapeHtml4(value));
    }
}
