package com.tads.senacflix.controller;

import com.tads.senacflix.dto.PersonagemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.PresentationDirection;

// Mapeia requisições HTTP para métodos ou classes de um Controller
@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @GetMapping("/favoritos")
    //ResponseEntity classe que representa a resposta HTTP completa
    public ResponseEntity<PersonagemDTO> buscarPersonagensFavoritos(){

        // RestTemplate abre uma conexão TCP
        // Envia os headers HTTP -> <- e lé a resposta
        RestTemplate template = new RestTemplate();

        // Definindo URL
        String url = "https://swapi.dev/api/people/2/";

        // getForObject faz o GET e converte a reposta para String
        PersonagemDTO reposta = template.getForObject(url, PersonagemDTO.class);

        // Devolve JSON da SWAPI
        return ResponseEntity.ok(reposta);
    }

    @GetMapping("/favoritos/{id}")
    public ResponseEntity<PersonagemDTO> buscarPorId(@PathVariable Long id) {
        try {
            RestTemplate template = new RestTemplate();
            String url = "https://swapi.dev/api/people/" + id + "/";

            PersonagemDTO personagem = template.getForObject(url, PersonagemDTO.class);

            return ResponseEntity.ok(personagem);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
