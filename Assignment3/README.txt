# Todo Manager Sprint 3

## Problem Statement

Todo Manager is an application, which can Manage our Tasks and keep track of our Tasks. In
the course of 3 week we will be creating a Todo manager.

## User Stories:-

1. As a user I should be able to add the task into an array
2. As a user I should be able to update the task in the array
3. As a user I should be able to delete the task in the array
4. As a user I should be able to search a task from an array
5. As a user I should be able to assignee the task to an user.

## Instructions:-

1. Please refactor the code created in sprint 2.
2. Please create a task pojo.
3. The task object should have
	a. TaskId(number auto generated)
	b. TaskTitle(String entered by the user)
	c. TaskText( String entered by the user)
	d. assignedTo(String entered by the user)
4. Please use arrays to store the tasks objects.
5. All the CRUD operations should happen in a dao class. Main should contain only
menu.
6. The application can be exited only if user enters 0 to exit. Else user should see the
menu again and again.
