package DI

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Function to initialize Koin with an optional app declaration.
 *
 * This function sets up Koin dependency injection for the application.
 * It accepts an optional KoinAppDeclaration parameter that allows
 * for additional app-specific configurations when starting Koin.
 *
 * @param appDeclaration A lambda function for app-specific declarations
 *                       (default is an empty lambda).
 */
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    // Execute any additional app-specific declarations
    appDeclaration()
    // Load common Koin modules
    modules(commonModule())
}

/**
 * iOS-specific initialization function for Koin.
 *
 * This function calls the main initKoin function with an empty
 * app declaration, providing a default setup for Koin on iOS platforms.
 */
fun initKoin() = initKoin {}