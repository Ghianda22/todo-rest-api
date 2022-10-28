package com.test.todorestapi.service;

import com.test.todorestapi.model.ToDo;
import com.test.todorestapi.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo create (ToDo toDo) { return toDoRepository.save(toDo);}
    public ToDo update (ToDo toDo) { return toDoRepository.save(toDo);}
    public void deleteById(int id) { toDoRepository.deleteById(id);}
    public Iterable<ToDo> getAll() { return toDoRepository.findAll();}
    public Optional<ToDo> getById(int id) throws IllegalArgumentException{
        return toDoRepository.findById(id);
    }
}
