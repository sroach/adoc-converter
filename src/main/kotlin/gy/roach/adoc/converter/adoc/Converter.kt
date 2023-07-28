package gy.roach.adoc.converter.adoc

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Attributes
import org.asciidoctor.Options
import org.asciidoctor.SafeMode
import org.asciidoctor.jruby.AsciiDocDirectoryWalker
import java.io.File

class Converter {
    val asciidoctor: Asciidoctor = Asciidoctor.Factory.create()

    init {
        asciidoctor.requireLibrary("asciidoctor-diagram")
    }

    fun makeHtml(file: File): Converter {

           val attrs = Attributes.builder()
               .sourceHighlighter("rouge")
               .allowUriRead(true)
               .linkAttrs(true)
               .attribute("rouge-css", "style")
               .attribute("coderay-css", "class")
               .attribute("coderay-linenums-mode", "inline")
               .attribute("guy-flag", "&#127468;&#127486;")
               .attribute("panel-server", "http://localhost:8010/extension")
               .attribute("panel-webserver", "http://localhost:8010/extension")
               .dataUri(true)
               .copyCss(true)
               .noFooter(true)
               .attribute("tocbot")
               .build()
           val options = Options.builder()
               .backend("html")
               .attributes(attrs)
               .safe(SafeMode.UNSAFE)
               .build()
            val result = asciidoctor.convertFile(file, options)
            return this
       }
    fun makePdf(): Converter {
        val walker = AsciiDocDirectoryWalker("src/main/asciidoc")
        val files = walker.scan()
        val attrs = Attributes.builder()
            .sourceHighlighter("rouge")
            .allowUriRead(true)
            .dataUri(true)
            .copyCss(true)
            .noFooter(false)
            // .attribute("pdf-fontsdir", "src/main/resources/fonts")
            .attribute("pdf-themesdir", "src/asciidoc/themes")
            .attribute("pdf-theme", "my")
            .attribute("panel-server", "https://roach.gy/extension")
            .attribute("panel-webserver", "https://roach.gy/extension")
            .build()
        val options = Options.builder()
            .backend("pdf")

            .attributes(attrs)

            .safe(SafeMode.UNSAFE)
            .build()
        files.forEach {
            asciidoctor.convertFile(it, options)
        }
        //asciidoctor.convertDirectory(files, options)
        return this
    }
    fun makePdf(file: File): Converter {


        val attrs = Attributes.builder()
            .sourceHighlighter("rouge")
            .allowUriRead(true)
            .dataUri(true)
            .copyCss(true)
            .noFooter(false)
            // .attribute("pdf-fontsdir", "src/main/resources/fonts")
            .attribute("pdf-themesdir", "src/asciidoc/themes")
            .attribute("pdf-theme", "my")
            .attribute("panel-server", "https://roach.gy/extension")
            .attribute("panel-webserver", "https://roach.gy/extension")
            .build()
        val options = Options.builder()
            .backend("pdf")

            .attributes(attrs)

            .safe(SafeMode.UNSAFE)
            .build()
        asciidoctor.convertFile(file, options)
        return this
    }
}

