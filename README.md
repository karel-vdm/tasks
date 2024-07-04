# Tasks
An application that allows users to create and view tasks. Please note that the main focus of this project is the overarching archtecture, dependency inversion and avoiding tight coupling of features.

The UI still needs to be completed and styled.

##
Todo:
1) Split tasks as a feature into a seperate module. To further propmete decoupling of features. As in shown in https://github.com/karel-vdm/Android-Showcase/tree/master/movies
2) Add missing and negative cases for all usecases.
3) Add a update flow when a user clicks on a task.
4) Add a delete option when a user clicks on a task.
5) Add a filter option.
6) Add UI Styling.

## Archtecture

This project uses Clean Archtecture with MVVM and Domain Driven Design. From a concurrency perspective the project makes use of Kotlin Flow and StateFlow in the ViewModel.

A typical interaction with the Room database will look as follows 

#### Presentation
  View -> ViewModel (ViewModel collects Flow and updates StateFlow)
  ViewModel StateFlow<> -> UseCase Flow<>
  
#### Domain
  UseCase Flow<> -> Repository Flow<>

#### Data
  Repository Flow<> -> DataSource


## Design considerations

### 1) Clean Architecture
Clean Architecture is an architectural approach that emphasizes separation of concerns, testability, maintainability, and scalability. It aims to create a modular structure that is decoupled and independent of frameworks. 
####The key principles of Clean Architecture are:
  1) Separation of Concerns:
       Each layer has distinct responsibilities such as presentation, domain logic, and data handling. The specific responsibilities should not depend on the implementation details of other layers.
  2) Depenency Rule
       Higher level layers should not depend on lower level layers but abtractions instead.
  3) Testability:
       This decoupled system making use of dependency inversion makes it easily testable in isolation, without the need for complex setup or dependencies on external systems.
  4) Independence of Frameworks:
       The core business logic and rules should not be tightly coupled to any specific framework or technology. Frameworks should be external details.   
     

   


