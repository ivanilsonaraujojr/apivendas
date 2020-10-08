package br.com.ivanilsonjr.config.validacao;

public class ErroDeJsonDto {

	private String mensagem;
	private String localizacaoErro;
	
	public ErroDeJsonDto(String mensagem, String localizacaoErro) {
		this.mensagem = mensagem;
		this.localizacaoErro = localizacaoErro;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public String getLocalizacaoErro() {
		return localizacaoErro;
	}
}
