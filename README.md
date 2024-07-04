# tasks
An application that allows users to create and view tasks.

##
Todo:
1) Split tasks as a feature into a seperate module. To further propmete decoupling of features. As in shown in https://github.com/karel-vdm/Android-Showcase/tree/master/movies
2) Add missing and negative cases for all usecases.
3) Add a update flow when a user clicks on a task
4) Add a delete option when a user clicks on a task
5) Add a filter option
6) Add UI Styling

## Archtecture

This project uses Clean Archtecture with MVVM and Domain Driven Design. From a concurrency perspective the project makes use of Kotlin Flow and StateFlow in the ViewModel.

A typical interaction with the Room database will look as follows 

### Presentation
  View -> ViewModel (ViewModel collects Flow and updates StateFlow)
  ViewModel StateFlow<> -> UseCase Flow<>
  
### Domain
  UseCase Flow<> -> Repository Flow<>

### Data
  Repository Flow<> -> DataSource


   


