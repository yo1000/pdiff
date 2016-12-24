package com.yo1000.pdiff.util

import com.fasterxml.jackson.databind.ObjectMapper
import difflib.Delta
import difflib.DiffUtils

/**
 *
 * @author yo1000
 */
class PdfComparator() {
    fun compare(lines1: List<String>, lines2: List<String>): List<Delta<String>> {
        return DiffUtils.diff(lines1, lines2).deltas
    }

    fun compareAndToJson(lines1: List<String>, lines2: List<String>): String {
        return ObjectMapper()
                .writeValueAsString(compare(lines1, lines2).map {
                    mutableMapOf(
                            "type" to it.type,
                            "deletion" to mutableMapOf(
                                    "line" to it.original.position,
                                    "changes" to it.original.lines
                            ),
                            "addition" to mutableMapOf(
                                    "line" to it.revised.position,
                                    "changes" to it.revised.lines
                            )
                    )
                })
    }

    fun compareAndToDiff(lines1: List<String>, lines2: List<String>): String {
        return compare(lines1, lines2).joinToString(
                separator = "\n",
                transform = {
                    "@@ -${it.original.position + 1} +${it.revised.position + 1} @@\n" +
                    when(it.type) {
                        Delta.TYPE.CHANGE -> {
                                    it.original.lines.joinToString(separator = "\n-", prefix = "-", postfix = "\n") +
                                    it.revised.lines.joinToString(separator = "\n+", prefix = "+", postfix = "\n")
                        }
                        Delta.TYPE.DELETE -> {
                                    it.original.lines.joinToString(separator = "\n-", prefix = "-", postfix = "\n")
                        }
                        Delta.TYPE.INSERT -> {
                                    it.revised.lines.joinToString(separator = "\n+", prefix = "+", postfix = "\n")
                        }
                        else -> ""
                    }
                })
    }
}