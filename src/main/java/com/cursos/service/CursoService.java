package com.cursos.service;

import com.cursos.model.Curso;
import com.cursos.repository.CursoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
     private CursoRepository repo;
    public CursoService(CursoRepository repo){
        this.repo = repo;
    }

    public List<Curso> getAll(){
        return repo.findAll();
    }

    public Optional<Curso> findById(Long id){
        return repo.findById(id);
    }

    public Curso save(Curso curso){
        return repo.save(curso);
    }

    public Optional<Curso> update(long id, Curso curso) {
    Optional<Curso> optional = repo.findById(id);
    if (optional.isEmpty()) {
        return Optional.empty();
    }
    Curso cursoDb = optional.get();
    
    cursoDb.setName(curso.getName());
    cursoDb.setHorario(curso.getHorario());
    cursoDb.setCapacidad(curso.getCapacidad());
    
    return Optional.of(repo.save(cursoDb));
}

    public Boolean delete(long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
