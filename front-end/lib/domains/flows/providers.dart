
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'flow_repository.dart';
import 'flow_service.dart';


final flowServiceProvider = Provider((ref) {
  return FlowService(ref.watch(flowRepositoryProvider));
});

final flowRepositoryProvider = Provider((ref) => FlowRepository());
