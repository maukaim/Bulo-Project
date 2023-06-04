enum OrchestrableStatus{
  NEW,
  PENDING_START,
  RUNNING,
  PAUSED,
  CANCELLED,
  FAILED,
  SUCCESS,
  UNKNOWN
}

OrchestrableStatus getOrchestrableStatusFromString(String value) {
  for (OrchestrableStatus enumValue in OrchestrableStatus.values) {
    if (enumValue.name.toLowerCase() == value.toLowerCase()) {
      return enumValue;
    }
  }
  throw ArgumentError(
      "The provided string does not match any OrchestrableStatus values.");
}