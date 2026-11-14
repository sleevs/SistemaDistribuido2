package br.com.jsncartoesms.util; 

public enum OutboxStatus{
    PENDENTE,
    PROCESSANDO,
    PUBLICADO,
    DEAD_LETTER
}