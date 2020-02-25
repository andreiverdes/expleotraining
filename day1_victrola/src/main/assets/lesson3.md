##  WorkManager

This class actually schedules your WorkRequest and makes it run. It schedules WorkRequests in a way that spreads out the load on system resources, while honoring the constraints you specify.
* Add work constraints like network availability or charging status
* Schedule asynchronous one-off or periodic tasks
* Monitor and manage scheduled tasks
* Chain tasks together
* Ensures task execution, even if the app or device restarts
* Adheres to power-saving features like Doze mode

WorkManager is intended for tasks that are deferrable—that is, not required to run immediately—and required to run reliably even if the app exits or the device restarts. For example:
* Sending logs or analytics to backend services
* Periodically syncing application data with a server

### `Worker`
Defines a unit of work by implementing doWork();

### `WorkRequest`
Defines how and when the work should be done;
The are 2 types of `WorkRequests`:
* One-off requests `OneTimeWorkRequest` run just once, after conditions are met
* Periodic requests `PeriodicWorkRequest` run until cancelled

Options:
* .setConstraints(Constraints) - defined below
* .setInitialDelay(long, TimeUnit) - set a initial delay value, after conditions are met
* .setBackoffCriteria(BackoffPolicy, Duration) - set a retry policy in time if work fails
  * BackoffPolicy.LINEAR
  * BackoffPolicy.EXPONENTIAL
* .setInputData(Data) - add some input data
  * A <key, value> object, just like a Bundle object
  * Max 10kb of data
  * Use another mechanism for bigger values, like a DB
* .addTag(String)
  * used to identify tasks to query the WorkManager by tag
  * for instance one might want to cancel all work with a certain tag attached
  * `WorkManager.cancelAllWorkByTag(String)`

### `Constraints`
Add specific constraints for your work request of when and how the request to run

Options:
* .setRequiredNetworkType(NetworkType)
  * NetworkType.NOT_REQUIRED
  * NetworkType.CONNECTED -
  * NetworkType.METERED - costs money
  * NetworkType.UNMETERED - usually not expensive
  * NetworkType.NOT_ROAMING - costs more money
* .setRequiresBatteryNotLow(boolean)
* .setRequiresCharging(boolean)
* .setRequiresDeviceIdle(boolean)
* .setRequiresStorageNotLow(boolean)

### Work States
* BLOCKED - Chained `WorkRequest`s waiting for preceding tasks to be finished
* ENQUEUED
* SUCCEEDED
* FAILED
* CANCELLED

### Canceling work
`WorkManager.cancelWorkById(workRequest.getId());`

### Chaining Work
* `.beginWith()`
* `.then()`
* `.enqueue()`

### Handling recurring work
* `PeriodicWorkRequest`


## Exercises

1. Every time your phone connects to the internet, print a funny log
2. Create a one time work triggered by your phone being charged
3. Create a progress bar tied to an emulation of a long running task like an image upload
4. Chain 1 and 2 into a single work chain
5. Create a button for each 4 requests from above that cancels the request onClick()
6. Have fun :D






