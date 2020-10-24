package com.example.alumno.service;

import com.example.alumno.entities.Alumnos;
import com.example.alumno.repositories.AlumnosRepository;
import com.example.alumno.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImplement  extends BaseServiceImplement<Alumnos, Long> implements AlumnoService{

    @Autowired
    private AlumnosRepository alumnoRepository;

    public AlumnoServiceImplement (BaseRepository<Alumnos, Long> baseRepository) {

        super(baseRepository);
    }
}
