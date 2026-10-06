package com.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.model.Estudiante;
import com.cursos.service.EstudianteService;

@RestController 
@RequestMapping ("/estudiantes")
public class EstudianteController {
    private EstudianteService service;

    public EstudianteController(EstudianteService service){
        this.service=service;
    }

    @GetMapping()
    public ResponseEntity<List<Estudiante>> getAllEstudiantes(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getMethodName(@PathVariable Long id) {
        Optional<Estudiante> estudiante = service.findById(id);
        if(estudiante.isPresent()){
            return ResponseEntity.ok(estudiante.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Estudiante> save(@RequestBody Estudiante estudiante){
        service.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estudiante> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id,Estudiante estudiante){
        Optional<Estudiante> optional = service.update(id, estudiante);
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
