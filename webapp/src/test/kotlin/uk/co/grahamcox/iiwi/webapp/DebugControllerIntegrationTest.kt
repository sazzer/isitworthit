package uk.co.grahamcox.iiwi.webapp

import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.format.DateTimeFormatter
import org.junit.Test

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Integration test for the Debug Controller
 */
class DebugControllerIntegrationTest : ControllerTestBase() {
    /**
     * Test the request to get the current time
     * @throws Exception never
     */
    [Test]
    fun getNow() {
        val mvcResult = perform(get("/api/debug/now"))
                ?.andExpect(status()?.isOk())
                ?.andExpect(content()?.contentTypeCompatibleWith("text/plain"))
                ?.andReturn()

        // Ensure that what we got actually parses
        val contentAsString = mvcResult?.getResponse()?.getContentAsString()
        DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(contentAsString)
    }

    /**
     * Test the request to get the build info
     * @throws Exception never
     */
    [Test]
    public fun getBuildInfo() {
        perform(get("/api/debug/buildInfo"))
                ?.andExpect(status()?.isOk())
                ?.andExpect(content()?.contentTypeCompatibleWith("application/json"))
                ?.andExpect(jsonPath("$.[project.name]")?.value("Is It Worth It? - Webapp"))
                ?.andExpect(jsonPath("$.[project.version]")?.value("1.0-SNAPSHOT"))
                ?.andExpect(jsonPath("$.[build.time]")?.value("17 October 2014, 11:33:58 +00:00"))
                ?.andExpect(jsonPath("$.[git.revision]")?.value("874f62a68ea0f1b4a7edc94a7fef8e24cfa35898"))
    }

}