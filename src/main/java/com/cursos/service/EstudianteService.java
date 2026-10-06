package com.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.cursos.model.Estudiante;
import com.cursos.repository.EstudianteRepository;

@Service
public class EstudianteService {
     private EstudianteRepository repo;
    public EstudianteService(EstudianteRepository repo){
        this.repo = repo;
    }

    public List<Estudiante> getAll(){
        return repo.findAll();
    }

    public Optional<Estudiante> findById(Long id){
        return repo.findById(id);
    }

    public Estudiante save(Estudiante estudiante){
        return repo.save(estudiante);
    }

    public Optional<Estudiante> update(long id, Estudiante estudiante) {
    Optional<Estudiante> optional = repo.findById(id);
    if (optional.isEmpty()) {
        return Optional.empty();
    }
    Estudiante estudianteDb = optional.get();
    
    estudianteDb.setName(estudiante.getName());
    estudianteDb.setAge(estudiante.getAge());
    estudianteDb.setEmail(estudiante.getEmail());
    
    return Optional.of(repo.save(estudianteDb));
}

    public Boolean delete(long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
