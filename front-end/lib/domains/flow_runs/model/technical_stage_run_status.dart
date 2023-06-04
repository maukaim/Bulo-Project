enum TechnicalStageRunStatus{
  TO_BE_REQUESTED,
  REQUESTED,
  ACKNOWLEDGED,
  RUNNING,
  TO_BE_CANCELLED,
  CANCELLED,
  FAILED,
  SUCCESS
}

TechnicalStageRunStatus getTsrStatusFromString(String value) {
  for (TechnicalStageRunStatus enumValue in TechnicalStageRunStatus.values) {
    if (enumValue.name.toLowerCase() == value.toLowerCase()) {
      return enumValue;
    }
  }
  throw ArgumentError(
      "The provided string does not match any TechnicalStageRunStatus values.");
}