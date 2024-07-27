import platform.UIKit.UIDevice

/**
 * IOSPlatform represents the iOS platform and provides information about the device.
 *
 * This class implements the Platform interface and overrides the name property
 * to return the system name and version of the current iOS device.
 */
class IOSPlatform: Platform {
    // The name of the platform, combining the system name and system version
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()