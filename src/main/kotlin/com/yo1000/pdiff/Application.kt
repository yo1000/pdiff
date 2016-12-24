package com.yo1000.pdiff

import com.yo1000.pdiff.service.PdfDifferenceService
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.File
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

/**
 *
 * @author yo1000
 */
@SpringBootApplication
open class Application(val pdfDifferenceService: PdfDifferenceService) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val options = Options()
        options.addOption("f1", "file1", true, "File to compare 1. (Must use with f2.)")
        options.addOption("f2", "file2", true, "File to compare 2. (Must use with f1.)")
        options.addOption("d1", "dir1", true, "Directory containing files to compare 1. (Must use with d2.)")
        options.addOption("d2", "dir2", true, "Directory containing files to compare 2. (Must use with d1.)")

        val parser = DefaultParser()
        val cl = parser.parse(options, args)

        if (cl.hasOption("f1") && cl.hasOption("f2")) {
            val file1 = cl.getOptionValue("f1")
            val file2 = cl.getOptionValue("f2")

            println("f1: $file1")
            println("f2: $file2")
            println()

            println(pdfDifferenceService.diff(
                    File(file1),
                    File(file2)
            ))
        }

        if (cl.hasOption("d1") && cl.hasOption("d2")) {
            val dir1 = Paths.get(cl.getOptionValue("d1"))
            val dir2 = Paths.get(cl.getOptionValue("d2"))

            Files.walkFileTree(dir1, object : SimpleFileVisitor<Path>() {
                override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                    val file2 = dir2.resolve(file.fileName)

                    if (!Files.exists(file2)) {
                        return super.visitFile(file, attrs)
                    }

                    println("f1: $file")
                    println("f2: $file2")
                    println()

                    println(pdfDifferenceService.diff(
                            file.toFile(),
                            dir2.resolve(file.fileName).toFile()))

                    return super.visitFile(file, attrs)
                }
            })
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
