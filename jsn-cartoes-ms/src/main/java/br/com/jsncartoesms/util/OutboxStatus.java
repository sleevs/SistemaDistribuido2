package br.com.jsncartoesms.util; 

public enum OutboxStatus{
    PENDENTE,
    PROCESSANDO,
    PUBLIADO,
    DEAD_LETTER
}