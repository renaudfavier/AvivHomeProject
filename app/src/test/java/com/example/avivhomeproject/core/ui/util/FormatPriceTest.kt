package com.example.avivhomeproject.core.ui.util

import com.example.avivhomeproject.core.presentation.util.formatWithSpaceBetweenThousands
import org.junit.Test
import kotlin.test.assertEquals

class FormatPriceTest {

    @Test
    fun `Double_formatWithSpaceBetweenThousands adds space between thousands`() {
        assertEquals("85", 85.0.formatWithSpaceBetweenThousands())
        assertEquals("850 000,33", 850000.33.formatWithSpaceBetweenThousands())
        assertEquals("850 000,03", 850000.03.formatWithSpaceBetweenThousands())
        assertEquals("850 000,30", 850000.3.formatWithSpaceBetweenThousands())
    }

    @Test
    fun `Int_formatWithSpaceBetweenThousands adds space between thousands`() {
        assertEquals("85", 85.formatWithSpaceBetweenThousands())
        assertEquals("850 000", 850000.formatWithSpaceBetweenThousands())
        assertEquals("999", 999.formatWithSpaceBetweenThousands())
        assertEquals("1 000", 1000.formatWithSpaceBetweenThousands())
        assertEquals("10 000 000", 10_000_000.formatWithSpaceBetweenThousands())
    }
}
