package com.callcenter.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ficha {
    private final Integer id;
    private final String nomeCliente;
    private final NivelPrioridade prioridade;
    private final LocalDateTime horaChegada;
    private LocalDateTime horaInicioAtendimento;
    private LocalDateTime horaEncerramento;

    public Ficha (Integer id, String nomeCliente, NivelPrioridade prioridade, LocalDateTime horaChegada) {

        this.id = id;
        this.nomeCliente = nomeCliente;
        this.prioridade = prioridade;
        this.horaChegada = horaChegada;
        this.horaInicioAtendimento = null;
        this.horaEncerramento = null;
    }

    public void iniciarAtendimento() {
        this.horaInicioAtendimento = LocalDateTime.now();
    }

    public void encerrarAtendimento(){
        this.horaEncerramento = LocalDateTime.now();
    }

    public Integer getId(){
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ficha outra = (Ficha) obj;
        return this.id.equals(outra.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    public Duration calcularTempoEspera(){
        if(horaInicioAtendimento == null){
            return Duration.between(horaChegada, LocalDateTime.now());
        }
        else {
            return Duration.between(horaChegada, horaInicioAtendimento);
        }
    }

    public Duration calcularTempoAtendimento(){
        if(horaEncerramento == null) {
            return Duration.between(horaInicioAtendimento, LocalDateTime.now());
        }
        else {
            return Duration.between(horaInicioAtendimento, horaEncerramento);
        }
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public NivelPrioridade getPrioridade() {
        return prioridade;
    }

    public LocalDateTime getHoraChegada() {
        return horaChegada;
    }

}

