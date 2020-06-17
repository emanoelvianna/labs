package com.margarida.loja.service;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.margarida.loja.dto.CompraDTO;
import com.margarida.loja.dto.InfoFornecedorDTO;

public class CompraService {

	public InfoFornecedorDTO comprar(CompraDTO compra) {
		String request = "http:localhost:8081/margarida-fornecedor/info" + compra.getEndereco().getEstado();

		RestTemplate client = new RestTemplate();
		return client.exchange(request, HttpMethod.GET, null, InfoFornecedorDTO.class).getBody();
	}

}
