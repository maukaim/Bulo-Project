enum StageType{
  technical,
  functional
}

StageType getStageTypeFromString(String value) {
  for (StageType enumValue in StageType.values) {
    if (enumValue.name.toLowerCase() == value.toLowerCase()) {
      return enumValue;
    }
  }
  throw ArgumentError(
      "The provided string does not match any StageType values.");
}