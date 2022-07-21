package com.gabriel.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.domain.Cliente;
import com.gabriel.os.domain.Pessoa;
import com.gabriel.os.dtos.ClienteDTO;
import com.gabriel.os.repositories.ClienteRepository;
import com.gabriel.os.repositories.PessoaRepository;
import com.gabriel.os.resources.exceptions.DataIntegratyViolationException;
import com.gabriel.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + " Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa pessoa = pessoaRepository.findByCPF(objDTO.getCpf());
		if(pessoa != null) {
			return pessoa;
		}
		return null;
	}
	

}
