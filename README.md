# ListenerSupport
A very lightweight library, that simplifies the usage of custom listeners. It is inspired
by the `PropertyChangeSupport` but it has the advantage of a custom listener implementation
like type safety.  

## ListenerSupport Features
As soon as you are going to create a listener interface for your view, model, controller or any other part of
your application, you have to write a lot of boilerplate code to manage and notify this listeners. This is where 
the `ListenerSupport` comes in place.  

The `ListenerSupport` is capable of managing your listeners (add or remove them). The notification of these registered 
listeners is only one method call.  

On top of that, you can...
- define `Executor`s that will be used to do this notification (e.g. do a notification in the 
UI-Thread). 
- define `FailureStrategie`s that will be called, when any listener throws an exception on notification call.

## ListenerSupport Creation
The following example will use the following listener interface definition.
```java
public interface MyListener {
    void onSomethingHappened( final String something );
}
```
To create a `ListenerSupport` for such a listener implementation you simply use the
`ListenerSupport` class.
```java
myListenerSupport = ListenerSupport.createFor( MyListener::onSomethingHappened );
```
This simple call will create a ListenerSupport that enables you to add and remove
listeners of type `MyListener` and provides a `notify()`-method with argument type 
`String` (like defined in the listener interface).  

## Notify Executors
In addition to the example above you can pass an `Executor` as second parameter to the 
`ListenerSupport#createFor()` method, which will be used to notify the registered
listeners. 
```java
myListenerSupport = ListenerSupport.createFor( MyListener::onSomethingHappened, NotifyExecutors.uiThreadExecutor() );
```
If no `Executor` is passed, the listeners it'll notify the listeners in the thread that called
the `notify()` method.

## Failure Strategies
As there can be errors thrown by listeners that are notified. There is the possibility
to register a `FailureStrategy` to the `ListenerSupport`. That `FailureStrategy` is
called whenever a listener has thrown an exception.

To be continued...