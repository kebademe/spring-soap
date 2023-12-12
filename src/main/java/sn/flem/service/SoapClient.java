package sn.flem.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Component
public class SoapClient {

    private final RestTemplate restTemplate;

    public SoapClient(@Lazy RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public  String sendSoapRequest(String requestXml,String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestXml, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        Assert.isTrue(responseEntity.getStatusCode().is2xxSuccessful(), "Error sending SOAP request");

        String responseXml = responseEntity.getBody();

        return responseXml;
    }



}
