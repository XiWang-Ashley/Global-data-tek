package com.infosys.service;

import com.infosys.dao.UserDAO;
import com.infosys.pojo.Task;
import com.infosys.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private TaskService taskService;
    private UserDAO userDAO;
    private User loggedInUser;

    public UserService(TaskService taskService) {
        this.users = new ArrayList<>();
        this.taskService = taskService;
        this.userDAO = new UserDAO();
    }

    // register
    public void registerUser(String username, String password) {
        try {
            userDAO.addUser(new User(username, password, "Client"));
            System.out.println("User registered successfully.");
        } catch (DuplicateUserException e) {
            System.out.println(e.getMessage());
        }
    }

    //login
    public boolean loginUser(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
            System.out.println("Invalid username or password. Please try again.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void logoutUser() {
        loggedInUser = null;
    }

    private boolean isLoggedInWithRole(String role) {
        return loggedInUser != null && loggedInUser.getRole().equals(role);
    }

    public void showTasks(){
        if (isLoggedInWithRole("Client")) {
            taskService.showTasks();
        } else {
            System.out.println("Only clients can show tasks.");
        }
    }

    public void addTask() {
        if (isLoggedInWithRole("Client")) {
            taskService.addTask();
        } else {
            System.out.println("Only clients can add tasks.");
        }
    }

    public void updateTaskByID() {
        if (isLoggedInWithRole("Client")) {
            taskService.updateTaskByID();
        }else{
            System.out.println("Only clients can update tasks.");
        }
    }

    public void deleteTaskByID() {
        if (isLoggedInWithRole("Client")) {
            taskService.deleteTaskByID();
        } else {
            System.out.println("Only clients can delete tasks.");
        }
    }

    public void deleteTaskByName(){
        if (isLoggedInWithRole("Client")) {
            taskService.deleteTaskByName();
        } else {
            System.out.println("Only clients can delete tasks.");
        }
    }

    public void searchTask(){
        if (isLoggedInWithRole("Client")) {
            taskService.searchTask();
        } else {
            System.out.println("Only clients can search tasks.");
        }
    }

    public void assignTaskToUser(){
        if (isLoggedInWithRole("Client")) {
            String visitorName = taskService.assignTaskToUser();
            try {
                User visitorUser = userDAO.getUserByUsername(visitorName);
                taskService.assignTaskToUser(visitorUser);
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                try {
                    User visitorUser = new User(visitorName, "visitorPassword", "Visitor");
                    userDAO.addUser(visitorUser);
                    taskService.assignTaskToUser(visitorUser);
                } catch (DuplicateUserException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (TaskNotFoundException | InvalidTaskOperationException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Only clients can search tasks.");
        }
    }

    public ArrayList<Task> getTasksForVisitor() {
        if (isLoggedInWithRole("Visitor")) {
            try {
                return taskService.getTasksByUserName(loggedInUser.getUsername());
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            System.out.println("Only visitors can view their tasks.");
            return null;
        }
    }

    public void showTasksForVisitor(){
        taskService.showTasks(getTasksForVisitor());
    }
    

    public void markTaskAsCompleted(){
        if(isLoggedInWithRole("Visitor")){
            taskService.markTaskAsCompleted(loggedInUser);
        }
    }
    public void showTasksInIncreasingOrder(){
        if(loggedInUser!=null){
            taskService.showTasksInIncreasingOrder(loggedInUser);
        }
    }

    public void showTasksInDecreasingOrder(){
        if(loggedInUser!=null){
            taskService.showTasksInDecreasingOrder(loggedInUser);
        }
    }

    public void showCompletedTasks() {
        if (isLoggedInWithRole("Visitor")) {
            taskService.showCompletedTasks(loggedInUser);
        }
    }

    public void showIncompleteTasks(){
        if(isLoggedInWithRole("Visitor")){
            taskService.showIncompleteTasks(loggedInUser);
        }
    }



}
