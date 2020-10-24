package com.example.alumno.repositories;

import com.example.alumno.entities.Alumnos;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends BaseRepository<Alumnos, Long> {
}

