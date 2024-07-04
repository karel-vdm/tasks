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

### Clean Architecture
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
     
### MVVM
MVVM helps to further separate the business and presentation logic of your application from the UI layer, making your code more modular, testable, and easier to maintain. 

Since Android is advocating and building it's components with MVVM in mind I would advocate for the use of MVVM. Most new android components are built with the setup in mind that they will be used from an Android base ViewModel from my experience there are quite a few side effects and additional considerations to be taken into account when this is not the case.

### Android Navigation Component
This makes navigation much easier and is the android reccomended method of navigation. Again from experience I have found that the Android ecosystem as a whole works best when following Android best practises. The main objective is ease of use and avoiding side effects to out of the box behaviour.

### Room Database
Again it is very easy to setup and use and works with Android reccomended patterns and best practises out of the box. It also has support for Kotlin Flow and KMP support is in Alpha

### Koin Dependency Injection
I usually use Hilt that also has KMP support but for this project with KMP in mind I wanted to use a more Kotlin based framework for dependency injection. Koin is easy to setup but run time debbugin can b a bit of an issue.

### StateFlow, Flow
I find using StateFlow with a Sealed class as a very clean and readable apprach to reacting to events on the ViewModel

   


