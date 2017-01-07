[![Build Status](https://travis-ci.org/ISchwarz23/ListenerSupport.svg?branch=master)](https://travis-ci.org/ISchwarz23/ListenerSupport) 
[![Download](https://api.bintray.com/packages/ischwarz/maven/ListenerSupport/images/download.svg)](https://bintray.com/ischwarz/maven/ListenerSupport/_latestVersion)

# ListenerSupport
A very lightweight library, that simplifies the usage of custom listeners. It is inspired
by the `PropertyChangeSupport` but it has the advantage of a custom listener implementation
like type safety.  
![ListenerSupport-Logo](https://raw.githubusercontent.com/ISchwarz23/ListenerSupport/master/readme/listenersupport-logo-with-name.png)  
**Minimum Java-Version:** 8  |  **Compile Java-Version:** 8  |  **Latest Library Version:** 0.9.0   

## Features
As soon as you are going to create a listener interface for your view, model, controller or any other part of
your application, you have to write a lot of boilerplate code to manage and notify this listeners, like...
- manage a collection to store the listeners
- add new listeners to that collection (after checking that it is not part of it, yet)
- removing old listeners from that collection (after checking that it was part of it)
- iterate over the whole collection to notify these listeners with the specific params
- catch exceptions thrown by the listeners to avoid errors
- make sure the notification is done in the correct thread
- ...

This is where the `ListenerSupport` library comes in place. The `ListenerSupport` simplifies the 
management of your listeners. Adding, removing and notifying them is now only ONE method call.  

On top of that, you can...
- define `Executor`s that will be used to do this notification (e.g. do a notification in the 
UI-Thread). 
- define `FailureStrategie`s that will be called, when any listener throws an exception on notification call.

## Add it to your Project
1) Add JCenter to your artifact repositories
```groovy
// using gradle
repositories {
    jcenter()
}
```
2) Add the ListenerSupport to your dependencies
```groovy
// using gradle
dependencies {
    compile 'de.codecrafters.listenersupport:listenersupport:<version>'
}
```

## ListenerSupport Creation
The following example will the listener interface definition below.
```java
public interface MyListener {
    void onSomethingHappened( final String something );
}
```
To create a `ListenerSupport` for any listener definition, you simply use the
`ListenerSupport` class.
```java
myListenerSupport = ListenerSupport.createFor( MyListener::onSomethingHappened );
```
This simple call will create a ListenerSupport that enables you to add and remove
listeners of type `MyListener` and provides a `notifyListeners()`-method which takes the same arguments
as the `MyListener#onSomethingHappened` method.  

## Manage Listeners
As described in the "ListenerSupport Creation" section, after the creation you are able to use
type safe methods to add, remove and notify listeners.  

So if we use the example above, the `myListenerSupport` will provide the following methods (among others):  
`myListenerSupport#addListener( MyListener )`  
`myListenerSupport#removeListener( MyListener )`  
`myListenerSupport#notifyListeners( String )`  

The most interesting of these methods is the notify method, which has exactly the same arguments as the 
method that shall be called (in this case it's the `MyListener#onSomethingHappened` method).

## Notify Executors
To define in which thread the notification is done, or if the notifications are done serial or in 
parallel you can give an `Executor` that will be used. Simply pass the `Executor` that shall be used
for notification as second parameter to the `ListenerSupport#createFor()` method. 
```java
myListenerSupport = ListenerSupport.createFor( MyListener::onSomethingHappened, NotifyExecutors.uiThreadExecutor() );
```
If no `Executor` is passed, it'll notify the listeners in the thread from which the `notify()` method 
was called.

## Failure Strategies
As there can be errors thrown by listeners that are notified, there is the possibility
to register a `FailureStrategy` to the `ListenerSupport`. That `FailureStrategy` is
called whenever a listener has thrown a `Throwable`.

There are already multiple `FailureStrategie`s available to be used:  

| Name | Artifact |
| --- | --- |
| JavaLoggingFailureStrategy | `de.codecrafters.listenersupport:failurestrategy-javalogger` |
| Log4jFailureStrategy | `de.codecrafters.listenersupport:failurestrategy-log4j` |
| PrintStackTraceFailureStrategy | none (set if no other available) |
| Slf4jFailureStrategy | `de.codecrafters.listenersupport:failurestrategy-slf4j` |
| SystemErrorFailureStrategy | `de.codecrafters.listenersupport:failurestrategy-systemerrorwriter` |

If you want to apply any of these failure strategies, just add it as runtime dependency to your project. It'll be
automatically used for every `ListenerSupport` you create.
If you have multiple failure strategies in your classpath you can define the default one by creating a property file
called "failurestrategyloader.properties" and set the name of the strategy that shall be the default for the
property called "strategyName".  

If you need to set the failure strategy different for any `ListenerSupport` just use the 
`ListenerSupport#setFailureStrategy()` method.

If you want to have all failure strategies available just use the `de.codecrafters.listenersupport:failurestrategy-all` 
artifact. This one also contains a factory called `FailureStrategies` that simplifies the creation for all failure
strategies.  

If the available failure strategies are not fulfilling your needs, you could of cause create your custom strategy.

#### JavaLoggingFailureStrategy
A `FailureStrategy` implementation that will log the thrown errors using the `java.util.logging.Logger`. The used 
log level is `ERROR`.

#### Log4jFailureStrategy
A `FailureStrategy` implementation that will log the thrown errors using the `org.apache.logging.log4j.Logger`. The 
used log level is `ERROR`.

#### PrintStackTraceFailureStrategy
A `FailureStrategy` implementation that will print the thrown errors using the `Throwable#printStackTrace()` method.

#### Slf4jFailureStrategy
A `FailureStrategy` implementation that will log the thrown errors using the `org.slf4j.Logger`. The used log level 
is `ERROR`.

#### SystemErrorFailureStrategy
A `FailureStrategy` implementation that will print the thrown errors using the `System.err` print stream 
(like `Throwable#printStackTrace(System.err)`) after printing the class information of the listener which has thrown
this error.
