package br.ufu.apiticketssuporte.excecao;

public class RecursoNaoEncontradoExcecao extends RuntimeException{
    public RecursoNaoEncontradoExcecao(String message) {
        super(message);
    }
}
