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

import com.cursos.model.Curso;
import com.cursos.service.CursoService;



@RestController
@RequestMapping("/cursos")
public class CursoController {

    private CursoService service;

    public CursoController(CursoService service){
        this.service=service;
    }

    @GetMapping()
    public ResponseEntity<List<Curso>> getAllCursos(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getMethodName(@PathVariable Long id) {
        Optional<Curso> curso = service.findById(id);
        if(curso.isPresent()){
            return ResponseEntity.ok(curso.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Curso> save(@RequestBody Curso curso){
        service.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id,Curso curso){
        Optional<Curso> optional = service.update(id, curso);
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
