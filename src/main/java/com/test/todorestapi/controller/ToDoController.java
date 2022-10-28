package com.test.todorestapi.controller;

import com.test.todorestapi.model.ToDo;
import com.test.todorestapi.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    @Autowired
    ToDoService toDoService;

    @PostMapping("/new")
    public ResponseEntity<ToDo> createToDo(@RequestBody String toDoDescription){
        ToDo toDo = new ToDo();
        toDo.setDescription(toDoDescription);
        return new ResponseEntity<ToDo>(toDoService.create(toDo), HttpStatus.OK);
    }

    @GetMapping("")
    public List<ToDo> getToDoList(){
        return (List<ToDo>) toDoService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") int id){
        try {
            Optional<ToDo> toDo = toDoService.getById(id);
            return toDo.isPresent()
                ? new ResponseEntity<>(toDo.get(),HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>("Id must not be null\n" + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable(value = "id") int id, @RequestBody ToDo toDoWithUpdates){
        try {
            Optional<ToDo> toDoToUpdate = toDoService.getById(id);
            if(toDoToUpdate.isPresent()){
                toDoWithUpdates.setId(id);
                return new ResponseEntity<>(toDoService.update(toDoWithUpdates),HttpStatus.OK);
            }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
    
    @PutMapping("/togglecompleted/{id}")
    public ResponseEntity<ToDo> toggleCompleted(@PathVariable(value = "id") int id){
        try {
            Optional<ToDo> toDoToUpdate = toDoService.getById(id);
            if(toDoToUpdate.isPresent()){
                ToDo completedToDo = toDoToUpdate.get();
                completedToDo.setCompleted(!completedToDo.isCompleted());
                return new ResponseEntity<>(toDoService.update(completedToDo),HttpStatus.OK);
            }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteToDo(@PathVariable(value = "id") int id){
        toDoService.deleteById(id);
    }

}
