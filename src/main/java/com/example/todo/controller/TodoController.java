package com.example.todo.controller;

import com.example.todo.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    List<Todo> list = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String task, Model model) {
        list.add(new Todo(task));
        model.addAttribute("list", list);
        return "list";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("list", list);
        return "list";
    }
}
