package sn.flem.util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils {


    /**
     *
     * @param xml avec le format <?xml version="1.0" encoding="utf-8"?>
     *       <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
     *           <soap:Body>
     *               <m:NumberToDollarsResponse xmlns:m="http://www.dataaccess.com/webservicesserver/">
     *                   <m:abcResult>four thousand five hundred dollars</m:abcResult>
     *               </m:NumberToDollarsResponse>
     *           </soap:Body>
     *       </soap:Envelope>
     * @param tClass
     * @param path exemple ==> Body.NumberToDollarsResponse
     * @return
     * @param <T>
     * @throws Exception
     */

    public static <T> T  xmlToObject(String xml, Class<T> tClass,String path) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode objectNode = xmlMapper.readTree(xml.getBytes());
        String[] paths = path.split("\\.");
        for(String pathName:paths)
            objectNode = objectNode.path(pathName);

        return objectMapper.treeToValue(objectNode,tClass);
    }



}
