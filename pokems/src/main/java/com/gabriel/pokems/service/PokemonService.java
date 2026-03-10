package com.gabriel.pokems.service;
import com.gabriel.pokems.model.Pokemon;
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
public class PokemonService {
	Logger logger = LoggerFactory.getLogger(PokemonService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/pokemon";

	protected static PokemonService service= null;
	public static PokemonService getService(){
		if(service == null){
			service = new PokemonService();
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

	public Pokemon get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Pokemon> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Pokemon.class);
		return response.getBody();
	}

	public Pokemon[] getAll() {
		String url = endpointUrl;
		logger.info("getPokemons: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Pokemon[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Pokemon[].class);
		Pokemon[] pokemons = response.getBody();
		return pokemons;
	}

	public Pokemon create(Pokemon pokemon) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Pokemon> request = new HttpEntity<>(pokemon, headers);
		final ResponseEntity<Pokemon> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Pokemon.class);
		return response.getBody();
	}
	public Pokemon update(Pokemon pokemon) {
		logger.info("update: " + pokemon.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Pokemon> request = new HttpEntity<>(pokemon, headers);
		final ResponseEntity<Pokemon> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Pokemon.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Pokemon> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Pokemon> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Pokemon.class);
	}
}
