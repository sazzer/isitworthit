package uk.co.grahamcox.iiwi.steam

import org.jetbrains.spek.api.*

class MethodDescriptionsTest : Spek() {{
    given("A set of Method Descriptions") {
        val methodDescriptions = MethodDescriptions(methods = listOf(
            MethodDescription(interface = "ISteamApps", name = "GetAppList", version = 1, method = "GET", parameters = listOf<ParameterDescription>())
        ))
        
        on("Looking up a method matching interface, name and version") {
            val interface = "ISteamApps"
            val name = "GetAppList"
            val version = 1
            
            it("Should be defined") {
                shouldBeTrue(methodDescriptions.isMethodAvailable(interface, name, version))
            }
            it("Should return the correct method description") {
                val method = methodDescriptions.findMethod(interface, name, version)
                shouldNotBeNull(method)
                
                shouldEqual("ISteamApps", method!!.interface)
                shouldEqual("GetAppList", method!!.name)
                shouldEqual(1, method!!.version)
                shouldEqual("GET", method!!.method)
                shouldEqual(0, method!!.parameters.size)
            }
        }
        on("Looking up a method matching interface, name but not version") {
            val interface = "ISteamApps"
            val name = "GetAppList"
            val version = 2

            it("Should not be defined") {
                shouldBeFalse(methodDescriptions.isMethodAvailable(interface, name, version))
            }
            it("Should not return a method description") {
                shouldBeNull(methodDescriptions.findMethod(interface, name, version))
            }
        }
        on("Looking up a method matching interface but not name and version") {
            val interface = "ISteamApps"
            val name = "Unknown"
            val version = 2

            it("Should not be defined") {
                shouldBeFalse(methodDescriptions.isMethodAvailable(interface, name, version))
            }
            it("Should not return a method description") {
                shouldBeNull(methodDescriptions.findMethod(interface, name, version))
            }
        }
        on("Looking up a method matching none of interface, name and version") {
            val interface = "Unknown"
            val name = "Unknown"
            val version = 2

            it("Should not be defined") {
                shouldBeFalse(methodDescriptions.isMethodAvailable(interface, name, version))
            }
            it("Should not return a method description") {
                shouldBeNull(methodDescriptions.findMethod(interface, name, version))
            }
        }
        on("Looking up a method matching name and version but not interface") {
            val interface = "Unknown"
            val name = "GetAppList"
            val version = 1

            it("Should not be defined") {
                shouldBeFalse(methodDescriptions.isMethodAvailable(interface, name, version))
            }
            it("Should not return a method description") {
                shouldBeNull(methodDescriptions.findMethod(interface, name, version))
            }
        }
    }
}}