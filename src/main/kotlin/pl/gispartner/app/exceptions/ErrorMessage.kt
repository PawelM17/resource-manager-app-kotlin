package pl.gispartner.app.exceptions

class ErrorMessage(
    val statusCode: Int,
    val message: String,
    val timeStamp: Long,
)
