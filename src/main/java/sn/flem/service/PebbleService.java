package sn.flem.service;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import java.io.StringWriter;
import java.io.Writer;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PebbleService {

    private final PebbleEngine pebbleEngine;

    public String transform(Map<String,Object> context,String templateName) throws Exception {
        PebbleTemplate template = pebbleEngine.getTemplate(templateName);
        Writer writer = new StringWriter();
        template.evaluate(writer, context);
        return writer.toString();
    }

}
