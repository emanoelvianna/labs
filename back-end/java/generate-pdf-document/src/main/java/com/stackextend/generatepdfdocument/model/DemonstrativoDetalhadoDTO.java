package com.stackextend.generatepdfdocument.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.NoArgsConstructor;

public class DemonstrativoDetalhadoDTO {

	private String nomeInstituicaoFinanceira;
	private String nomeAssociado;
	private String codigoContrato;
	private String taxaJurosEfetiva;
	private String taxaJurosNominal;
	private String tipoTaxa;
	private Integer totalParcelasOperacao;
	private String dataLiberacaoOperacao;
	private String valorSaldoDevedorAtualizado;
	private String dataEmissao;
	private String documentoInstituicaoFinanceira;
	private String documentoAssociado;
	private String modalidadeBacenContrato;
	private String taxaJurosAnual;
	private String sistemaAmortizacao;
	private String indexador;
	private Integer prazoRemanescenteOperacao;
	private String dataUltimoVencimentoOperacao;
	private String dataInformacaoSaldoDevedor;
	// others
	private Integer idOperacao;
	private String dataValorDevedor;
	
	public DemonstrativoDetalhadoDTO(String nomeInstituicaoFinanceira) {
		this.nomeInstituicaoFinanceira = nomeInstituicaoFinanceira;
	}
	
	public String getNomeInstituicaoFinanceira() {
		return nomeInstituicaoFinanceira;
	}

}
