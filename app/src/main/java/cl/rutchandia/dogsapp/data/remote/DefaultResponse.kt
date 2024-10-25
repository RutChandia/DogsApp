package cl.rutchandia.dogsapp.data.remote

data class DefaultResponse(
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean
)