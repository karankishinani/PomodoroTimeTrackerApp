package com.ptt;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
class ProjectController {

    private final ProjectRepository repository;

    ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("ptt/api/users/{userid}/projects")
    List<Project> all() {
        return repository.findAll();
    }

    @PostMapping("ptt/api/users/{userid}/projects")
    @ResponseStatus(HttpStatus.CREATED)
    Project newProject(@RequestBody Project newProject) {
        return repository.save(newProject);
    }

    // Single item

    @GetMapping("ptt/api/users/{userid}/projects/{id}")
    Project one(@PathVariable String id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

    }

    @PutMapping("ptt/api/users/{userid}/projects/{id}")
    Project replaceProject(@RequestBody Project newProject, @PathVariable String id) {

        return repository.findById(id)
                .map(Project -> {
                    Project.setProjectname(newProject.getProjectname());
                    return repository.save(Project);
                })
                .orElseThrow(() -> new UserNotFoundException());


    }

    @DeleteMapping("ptt/api/users/{userid}/projects/{id}")
    Project deleteProjects(@PathVariable String id) {

        Project result = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        repository.deleteById(id);

        return result;
    }

    @DeleteMapping("ptt/api/users/{userid}/projects")
    List<Project> deleteAllProjects() {

        List<Project> result = repository.findAll();

        repository.deleteAll();

        return result;
    }
}