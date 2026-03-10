package com.gabriel.pokems.service;
import com.gabriel.pokems.model.CoinFlip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CoinFlipService {
	Logger logger = LoggerFactory.getLogger(CoinFlipService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/coinFlip";

	protected static CoinFlipService service= null;
	public static CoinFlipService getService(){
		if(service == null){
			service = new CoinFlipService();
		}
		return service;
	}

	RestTemplate restTemplate = null;
	public RestTemplate getRestTemplate() {
		if(restTemplate == null) {
		restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
		}
		return restTemplate;
	}

	public CoinFlip get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<CoinFlip> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, CoinFlip.class);
		return response.getBody();
	}

	public CoinFlip[] getAll() {
		String url = endpointUrl;
		logger.info("getCoinFlips: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<CoinFlip[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, CoinFlip[].class);
		CoinFlip[] coinFlips = response.getBody();
		return coinFlips;
	}

	public CoinFlip create(CoinFlip coinFlip) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<CoinFlip> request = new HttpEntity<>(coinFlip, headers);
		final ResponseEntity<CoinFlip> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, CoinFlip.class);
		return response.getBody();
	}
	public CoinFlip update(CoinFlip coinFlip) {
		logger.info("update: " + coinFlip.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<CoinFlip> request = new HttpEntity<>(coinFlip, headers);
		final ResponseEntity<CoinFlip> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, CoinFlip.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<CoinFlip> request = new HttpEntity<>(null, headers);
		final ResponseEntity<CoinFlip> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, CoinFlip.class);
	}
}
