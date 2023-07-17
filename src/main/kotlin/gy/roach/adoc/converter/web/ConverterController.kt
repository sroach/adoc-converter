package gy.roach.adoc.converter.web

import gy.roach.adoc.converter.adoc.Converter
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
@RequestMapping("/")
class ConverterController {

    @Value("\${webroot.dir}")
    private val dir: String = ""

    @GetMapping("/doc/*/*.adoc")
    fun getDocument(httpServletRequest: HttpServletRequest): ResponseEntity<String> {
        val path = httpServletRequest.requestURI.replace("/converter/doc/", "")
        println(path)
        val f = File(dir, path)
        val converter = Converter()
        converter.makeHtml(f)
        val fname = File(dir, path.replace(".adoc", ".html"))
        return ResponseEntity.ok(fname.readText())
    }
}