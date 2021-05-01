package models

class PriceFormatter {
    def apply(price: Double): String = {
        val locale = new java.util.Locale("de", "DE")
        val formatter = java.text.NumberFormat.getInstance(locale)

        s"${formatter.format(price)} €"
    }
}
