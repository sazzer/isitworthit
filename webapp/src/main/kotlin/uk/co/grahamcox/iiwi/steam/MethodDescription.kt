package uk.co.grahamcox.iiwi.steam

/**
 * Representation of a Parameter that a Method can take
 */
data class ParameterDescription(val name: String, 
    val type: String, 
    val optional: Boolean,
    val description: String)

/**
 * Representation of a Steam API Method that can be called
 */
data class MethodDescription(val interface: String, 
    val name: String, 
    val version: Int, 
    val method: String,
    val parameters: List<ParameterDescription>)
 
/**
 * Representation of the list of methods that are available to be called
 * @param methods the methods
 */
class MethodDescriptions(private val methods: List<MethodDescription>) {
    /**
     * Find the method description for the requested method
     * @param interface The interface to look for
     * @param name The method name to look for
     * @param version The version to look for
     * @return The method description if available
     */
    fun findMethod(interface: String, name: String, version: Int): MethodDescription? = 
        methods.find {
            interface == it.interface && name == it.name && version == it.version
        }

    
    /**
     * Check if the given method is available
     * @param interface The interface to look for
     * @param name The method name to look for
     * @param version The version to look for
     * @return True if the method is available. False if not
     */
    fun isMethodAvailable(interface: String, name: String, version: Int): Boolean = 
        findMethod(interface, name, version) != null
}