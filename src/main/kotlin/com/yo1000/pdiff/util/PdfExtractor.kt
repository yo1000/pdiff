package com.yo1000.pdiff.util

import org.apache.pdfbox.io.RandomAccessFile
import org.apache.pdfbox.pdfparser.PDFParser
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File

/**
 *
 * @author yo1000
 */
open class PdfExtractor {
    fun getText(file: File): String {
        return RandomAccessFile(file, "r").use<RandomAccessFile, String> {
            var parser = PDFParser(it)
            parser.parse()
            var doc = parser.pdDocument

            var stripper = PDFTextStripper()
            stripper.getText(doc)
        }
    }

    fun getLines(file: File): List<String> {
        return getText(file).split('\n')
    }
}
