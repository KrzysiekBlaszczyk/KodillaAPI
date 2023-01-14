package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    DbService dbService;
    @MockBean
    TaskMapper taskMapper;

    @Test
    void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        when(taskMapper.mapToTaskDto(dbService.getTask(1L))).thenReturn(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Content")));
    }

    @Test
    void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = List.of(new TaskDto(1L, "Title", "Content"));
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title", Matchers.is("Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].content", Matchers.is("Content")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        // Given
        dbService.deleteTask(1L);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto updatedTaskDto = new TaskDto(1L, "updatedTitle", "updatedContent");
        when(taskMapper.mapToTask(updatedTaskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(updatedTaskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTaskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("updatedTitle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("updatedContent")))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto createdTaskDto = new TaskDto(1L, "updatedTitle", "updatedContent");
        when(taskMapper.mapToTask(createdTaskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(createdTaskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdTaskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}



