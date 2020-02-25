## Lifecycle-Aware Components

### ViewModel
 Provides a way to create and retrieve objects that are bound to a  
 specific lifecycle. A ViewModel typically stores the state of a  
 view's data and communicates with other components, such as  
 data repositories or the domain layer which handles business logic.  
 To read an introductory guide to this topic,  
 see [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)

 - custom Factory

### LifecycleOwner
 LifecycleOwner is an interface implemented by the AppCompatActivity  
 and Fragment classes. You can subscribe other components to owner  
 objects which implement this interface, to observe changes to the  
 lifecycle of the owner. To read an introductory guide to this topic,  
 see [Handling Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle.html).

### LifecycleObserver
 A class can monitor the component's lifecycle status by adding  
 annotations to its methods. Then you can add an observer by calling  
 the addObserver() method of the Lifecycle class and passing an  
 instance of your observer.

### LiveData
 Allows you to observe changes to data across multiple components of  
 your app without creating explicit, rigid dependency paths between  
 them. LiveData respects the complex lifecycles of your app components,  
 including activities, fragments, services, or any LifecycleOwner  
 defined in your app. LiveData manages observer subscriptions by  
 pausing subscriptions to stopped LifecycleOwner objects,  
 and cancelling subscriptions to LifecycleOwner objects  
 that are finished. To read an introductory guide to this topic,  
 see LiveData.

* .postValue(value)
* .setValue(value)
* .observe(LifeCycleOwner, Observer)
