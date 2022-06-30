package com.gabriel.os.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.os.domain.Tecnico;
import com.gabriel.os.dtos.TecnicoDTO;
import com.gabriel.os.services.TecnicoService;

@RestController //classe rest := get, post, put...
@RequestMapping(value = "/tecnicos") // endpoint inicial
public class TecnicoResource {
	
	//localhost:8080/tecnicos/1
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
			TecnicoDTO objDTO = new TecnicoDTO(this.service.findById(id));
			
			return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<TecnicoDTO> listDTO = service.findAll().stream()
				.map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
		
//		List<Tecnico> list = this.service.findAll();
//		List<TecnicoDTO> listDTO = new ArrayList<TecnicoDTO>();
//		
//		for(Tecnico tecnico : list) {
//			listDTO.add(new TecnicoDTO(tecnico));
//		}
		
//		
		
//		list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
	}
}
