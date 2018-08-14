package com.dariosantos.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.dariosantos.cursomc.domain.PagamentoComBoleto;

@Service
public class boletoService {
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);//adiciona 7 dias na data informada
		pagto.setDataVencimento(cal.getTime());
	}
}
