package com.yo1000.pdiff.service

import com.yo1000.pdiff.util.PdfComparator
import com.yo1000.pdiff.util.PdfExtractor
import org.springframework.stereotype.Service
import java.io.File

/**
 *
 * @author yo1000
 */
@Service
class PdfDifferenceService(val pdfExtractor: PdfExtractor, val pdfComparator: PdfComparator) {
    fun diff(f1: File, f2: File): String {
        return pdfComparator.compareAndToDiff(
                pdfExtractor.getLines(f1),
                pdfExtractor.getLines(f2))
    }
}
