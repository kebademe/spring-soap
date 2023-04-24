package sn.flem.util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.Iterator;

public class XmlUtil {

    public static <T> T  xmlToObject(String xml, Class<T> tClass) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = xmlMapper.readTree(xml.getBytes());
        JsonNode bodyNode = rootNode.path("Body");
        Iterator<String> fieldNames = bodyNode.fieldNames();
        String fieldName = fieldNames.next();
        JsonNode objectNode = bodyNode.path(fieldName);
        return objectMapper.treeToValue(objectNode,tClass);
    }


}
