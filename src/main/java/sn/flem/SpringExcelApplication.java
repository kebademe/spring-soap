package sn.flem;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sn.flem.model.AddResponse;
import sn.flem.model.DollarResponse;

import sn.flem.service.PebbleService;
import sn.flem.service.SoapClient;
import sn.flem.util.XmlUtils;


@SpringBootApplication
public class SpringExcelApplication implements CommandLineRunner {
	@Autowired
	PebbleService pebbleService;
	@Autowired
	SoapClient soapClient;


	public static void main(String[] args) {
		SpringApplication.run(SpringExcelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//test consume soap api
		Map<String, Object> context = new HashMap<>();
		context.put("value1", 2);
		context.put("value2",100);
		String request = pebbleService.transform(context,"calculateur");

		String response = soapClient.sendSoapRequest(request,"http://www.dneonline.com/calculator.asmx");

		AddResponse addResponse = XmlUtils.xmlToObject(response,AddResponse.class,"Body.AddResponse");

		//test consume soap api
		Map<String, Object> context1 = new HashMap<>();
		context1.put("value", 23002);

		String request1 = pebbleService.transform(context1,"number_to_dollar");
		String response1 = soapClient.sendSoapRequest(request1,"https://www.dataaccess.com/webservicesserver/NumberConversion.wso");

		DollarResponse dollarResponse = XmlUtils.xmlToObject(response1,DollarResponse.class,"Body.NumberToDollarsResponse");



	}




}



