package edu.depaul.cdm.se452.group4.minuteTrak.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.ProjectDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.dto.ResponseDTO;
import edu.depaul.cdm.se452.group4.minuteTrak.model.ProjectEntity;
import edu.depaul.cdm.se452.group4.minuteTrak.security.AuthStatus;
import edu.depaul.cdm.se452.group4.minuteTrak.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

  @Autowired
  ProjectService projectService;

  @GetMapping("/list")
  public ResponseEntity<?> getProjectList() {
    List<ProjectEntity> entities = projectService.getAllProjectEntities();
    List<ProjectDTO> projectList = new ArrayList<>();

    for (ProjectEntity entity : entities) {
      projectList.add(ProjectDTO.builder().pId(entity.getPId()).name(entity.getName())
          .budget(entity.getBudget()).build());
    }

    return ResponseEntity.ok().body(projectList);
  }

  @PostMapping
  public ResponseEntity<?> newProject(@AuthenticationPrincipal AuthStatus authStatus,
      @RequestBody ProjectDTO projectDTO) {
    final String authRole = authStatus.getRole();
    if (!authRole.equals("admin")) {
      ResponseDTO<String> responseDTO =
          ResponseDTO.<String>builder().error("Only admin can create a new project").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }

    ProjectEntity projectEntity =
        ProjectEntity.builder().name(projectDTO.getName()).budget(projectDTO.getBudget()).build();
    ProjectEntity created = projectService.create(projectEntity); // TODO - exception handling

    return ResponseEntity.ok().body(created);
  }

}
