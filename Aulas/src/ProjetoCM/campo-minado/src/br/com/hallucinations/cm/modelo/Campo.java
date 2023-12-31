package br.com.hallucinations.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Campo {
	
	
	private final int linha;
	private final int coluna;
	
	private boolean aberto;// por padrão boolean é false!
	private boolean minado; 
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
	private List<CampoObservador> obsevadores = new ArrayList<>();
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}

	public void registrarObservador(CampoObservador observador){
		obsevadores.add(observador);
	}

	private void notificarObservadores(CampoEvento evento){
		obsevadores.stream().forEach(o->o.eventoOcorreu(this,evento));
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha ;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}	
	}
	
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
			if(marcado){
				notificarObservadores(CampoEvento.MARCAR);
			}else {
				notificarObservadores(CampoEvento.DESMARCAR);
			}
		}
	}
	
	boolean abrir() {
		
		if (!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				notificarObservadores(CampoEvento.EXPLODIR);
				return true;
			}
			setAberto(true);
			if(vizinhançaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	boolean vizinhançaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	void minar() {
		if(!minado) {
			minado = true;			
		}
		
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	 void setAberto(boolean aberto) {
		this.aberto = aberto;
		if(aberto){
			notificarObservadores(CampoEvento.ABRIR);
		}
	 }

	

	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	

	
}
