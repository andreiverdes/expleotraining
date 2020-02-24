Services

* Foreground
  * A foreground service performs some operation that is noticeable to the user
  * Needs to display a notification
* Background
  * If your app targets API level 26 or higher, the system imposes restrictions on running background services when the app itself isn't in the foreground. In most situations, for example, you shouldn't access location information from the background. Instead, schedule tasks using WorkManager.
* Bound
  * When an app component binds to it (creates a link) by calling `bindService()`
  * Multiple components can bind to the service at once, but when all of them unbind, the service is destroyed.

# THEY RUN ON THE MAIN THREAD
# THEY RUN ON THE MAIN THREAD
# THEY RUN ON THE MAIN THREAD
# THEY RUN ON THE MAIN THREAD
# THEY RUN ON THE MAIN THREAD
# THEY RUN ON THE MAIN THREAD

Use IntentService or a separate Thread

* onStartCommand
  * Invoked when the service is started with `startService()`
  * Lives indefinitely until `stopSelf()` or `stopService()` is called
* onBind()
  * Invoked when the service is started with `bindService()`
  * Lives until all components have unbound or until the bound context, dies
* onCreate()
  * Invoked to perform one-time setup procedures when the service is initially created (before it calls either `onStartCommand()` or `onBind()`)
* onDestroy()
  * Invoked when the service is no longer used and is being destroyed.
