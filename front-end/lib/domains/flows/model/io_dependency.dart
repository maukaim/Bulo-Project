
import 'input_provider.dart';

class IoDependency {
  final String inputId;
  final List<InputProvider> inputProviders;

  IoDependency({required this.inputId, required this.inputProviders});

  factory IoDependency.fromJson(Map<String, dynamic> json) {
    return IoDependency(
      inputId: json['inputId'],
      inputProviders: [
        for (var x in json['inputProviders']) InputProvider.fromJson(x)
      ],
    );
  }
}