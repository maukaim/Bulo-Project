
import 'package:bulo_ui/domains/flows/model/owner_type.dart';

class OwnerKeys{
  final String ownerId;
  final OwnerKeyType ownerType;
  OwnerKeys({required this.ownerId, required this.ownerType});

  factory OwnerKeys.fromJson(Map<String, dynamic> json) {
    return OwnerKeys(
      ownerId: json['ownerId'],
      ownerType: getEnumFromString(json['type']),
    );
  }
}
