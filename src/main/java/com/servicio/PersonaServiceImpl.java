package com.servicio;

import com.dao.PersonaDao;
import com.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Para que Spring la reconozca como clase de servicio
// Transactional -> si resulta todo se hace un commit, sino rollback
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private PersonaDao personaDao;
    
    @Override
    @Transactional(readOnly = true) // Solo vamos a leer
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional // Si modifica información, hace commit o rollback
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional // Si modifica información, hace commit o rollback
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true) // Solo vamos a leer
    public Persona encontrarPersona(Persona persona) {
        // Como regresa un Optional, puedo elegir que hacer si lanzar excepcion o null
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}