package object wrappers {
  case class ApiError(
    status: Int,
    statusText: Option[String] = None,
    data: Option[Any] = None
  ) extends Exception(statusText.orNull)
}
