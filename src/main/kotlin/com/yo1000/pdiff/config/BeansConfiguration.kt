package com.yo1000.pdiff.config

import com.yo1000.pdiff.util.PdfComparator
import com.yo1000.pdiff.util.PdfExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * @author yo1000
 */
@Configuration
open class BeansConfiguration {
    @Bean
    open fun pdfExtractor(): PdfExtractor {
        return PdfExtractor()
    }

    @Bean
    open fun pdfComparator(): PdfComparator {
        return PdfComparator()
    }
}