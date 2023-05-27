enum OwnerKeyType { user, team }

OwnerKeyType getEnumFromString(String value) {
  for (OwnerKeyType enumValue in OwnerKeyType.values) {
    if (enumValue.name == value.toLowerCase()) {
      return enumValue;
    }
  }
  throw ArgumentError(
      "The provided string does not match any OwnerKeyType values.");
}
