package com.flpoliveira.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.flpoliveira.cursomc.domain.enums.TipoCliente;
import com.flpoliveira.cursomc.dto.ClienteNewDTO;
import com.flpoliveira.cursomc.resources.exceptions.FieldMessage;
import com.flpoliveira.cursomc.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
/*
		if(objDto.getTipo() == null)
		{
			list.add(new FieldMessage("tipo", "Tipo nao pode ser nulo"));
		}
*/
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())
			) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		else if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPNJ(objDto.getCpfOuCnpj()))
		{
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}