import 'flow_repository.dart';

class FlowService {
  final FlowRepository _repository;
  //Empty map
  final Map<String, String> flowMap = {};

  FlowService(this._repository);

  List<String> getAvailableFlows() {
    if (flowMap.isEmpty) {
      refreshAll();
    }
    return flowMap.values.toList();
  }

  void refreshAll() {
    flowMap.clear();
    Map<String, String> tmp = {
      for (var flow in _repository.getAll()) flow: flow
    };
    flowMap.addAll(tmp);
  }
}
