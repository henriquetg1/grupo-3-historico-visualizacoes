package br.insper.loja.usuario.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class UsuarioService {

    public RetornarUsuarioDto getDataWithJwt(String url, String jwtToken){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<RetornarUsuarioDto> response = restTemplate.exchange(
          "http://localhost/8080/usuario",
                HttpMethod.GET,
                entity,
                RetornarUsuarioDto.class


        );

        return respose.getBody();

    }

}
