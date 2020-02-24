## Handlers

What are threads?
Why do we need handlers?

Java threads die after execution. We need a mechanism to be able to
keep such a thread alive even after execution in the eventuality some other
similar task needs to be executed.

Thread safe cross communication. IE: Sending messages back and forth
between 2 threads or more.

How would we implement such a thread that loops?
TASK: Let's implement such a thread!

Handler - Android component used to communicate between threads.
Usually used to communicate with the UI thread from the main thread.

MessageQueue - It's a queue that stores messages to be processed by the thread
Handler - A component that puts such messages onto the MessageQueue
* Looper - it keeps the thread alive by putting it into an infinite loop
  * .prepare() - identifies the calling thread and creates a looper and a message queue for that thread
  * .loop() - starts the looper to keep the thread alive
  * .quit() - termintates the looper to stop the thread

### Exercises
1. Create a chat-like app in one screen using a HandlerThread
   as a server to communicate.


