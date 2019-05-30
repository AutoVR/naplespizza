package com.np;

import static org.junit.Assert.assertNotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.vo.OrderVO;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NaplespizzaApplicationTests {

	@LocalServerPort
    private  int port;
	
	private static RestTemplate restTemplate;
	

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();
	
	private static JSONObject orderJson;
	
	private static JSONObject customerJson;
	
	private static JSONObject orderItemJson;
	
	private static JSONObject pizzaJson;
	
	private static long orderId;
	
	private String getOrderUrl() {
		return "http://localhost:" + port + "/orders/";
	}

    @BeforeClass
    public static void prePopulate() throws JSONException {

    	 restTemplate = new RestTemplate();
    	
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        customerJson = new JSONObject();
        customerJson.put("customerId", 1);
        
        pizzaJson = new JSONObject();
        pizzaJson.put("pizzaId", 1);
        
        orderJson = new JSONObject();
        orderJson.put("orderStatus", 1);
        orderJson.put("isDelivery", true);
        orderJson.put("customer", customerJson);
        
        orderItemJson = new JSONObject();
        orderItemJson.put("toppings", new JSONArray(new Object[] {"Onion", "Chicken", "Mushroom"}));
        orderItemJson.put("quantity", 3 );
        orderItemJson.put("pizza", pizzaJson );
    }
	
	
	
	@Test
	public void canCreateNewOrder() {
		
        HttpEntity<String> request = new HttpEntity<String>(orderJson.toString(), headers);

        OrderVO orderVO = restTemplate.postForObject(getOrderUrl(), request, OrderVO.class);

        assertNotNull(orderVO);
        assertNotNull(orderVO.getOrderId());
        
        orderId= orderVO.getOrderId();
        
	}
	
	@Test
	public void canCreateOrderItem() {
		
        HttpEntity<String> request = new HttpEntity<String>(orderItemJson.toString(), headers);

        OrderVO orderVO = restTemplate.postForObject(getOrderUrl()  + orderId + "/orderItems/", request, OrderVO.class);

        assertNotNull(orderVO);
        assertNotNull(orderVO.getOrderId());
        assertNotNull(orderVO.getCustomer());
        assertNotNull(orderVO.getOrderItems());
        assertNotNull(orderVO.getTotalPrice());
	}

}
