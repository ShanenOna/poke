package com.gabriel.pokems.service;
import com.gabriel.pokems.model.PokeLuck;
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
public class PokeLuckService {
	Logger logger = LoggerFactory.getLogger(PokeLuckService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/pokeLuck";

	protected static PokeLuckService service= null;
	public static PokeLuckService getService(){
		if(service == null){
			service = new PokeLuckService();
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

	public PokeLuck get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<PokeLuck> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, PokeLuck.class);
		return response.getBody();
	}

	public PokeLuck[] getAll() {
		String url = endpointUrl;
		logger.info("getPokeLucks: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<PokeLuck[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, PokeLuck[].class);
		PokeLuck[] pokeLucks = response.getBody();
		return pokeLucks;
	}

	public PokeLuck create(PokeLuck pokeLuck) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<PokeLuck> request = new HttpEntity<>(pokeLuck, headers);
		final ResponseEntity<PokeLuck> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, PokeLuck.class);
		return response.getBody();
	}
	public PokeLuck update(PokeLuck pokeLuck) {
		logger.info("update: " + pokeLuck.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<PokeLuck> request = new HttpEntity<>(pokeLuck, headers);
		final ResponseEntity<PokeLuck> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, PokeLuck.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<PokeLuck> request = new HttpEntity<>(null, headers);
		final ResponseEntity<PokeLuck> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, PokeLuck.class);
	}
}
