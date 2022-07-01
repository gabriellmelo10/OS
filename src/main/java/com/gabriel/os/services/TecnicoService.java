package com.gabriel.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.domain.Tecnico;
import com.gabriel.os.dtos.TecnicoDTO;
import com.gabriel.os.repositories.TecnicoRepository;
import com.gabriel.os.resources.exceptions.DataIntegratyViolationException;
import com.gabriel.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return this.repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!"); 
		}
		return this.repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Tecnico findByCPF(TecnicoDTO objDTO) {
		Tecnico tecnico = this.repository.findByCPF(objDTO.getCpf());
		if(tecnico != null) {
			return tecnico;
		}
		return null;
	}
}
