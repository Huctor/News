package Wrapper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * FlowWrapper is a utility class that provides a way to observe changes in a StateFlow.
 *
 * @param stateFlow The StateFlow to observe for changes.
 */
class FlowWrapper<T>(private val stateFlow: StateFlow<T>) {

    /**
     * Observes changes in the StateFlow and invokes the callback with the new value.
     *
     * This method launches a coroutine on the Main dispatcher to collect values
     * from the StateFlow and pass them to the provided callback function.
     *
     * @param callback A function that will be called with the new value each time
     *                 the StateFlow emits a new value.
     */
    fun observe(callback: (T) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            stateFlow.collect {
                callback(it)
            }
        }
    }
}