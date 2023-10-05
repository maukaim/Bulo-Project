import 'package:bulo_ui/core/connect/server_connector.dart';
import 'package:bulo_ui/widgets/main_window/nav/nav_tab/nav_menu_type.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final selectedTabMenuProvider = StateProvider.family<NavMenuType?, ServerConnector>(
        (ref, servConnector) => NavMenuType.flowMenu);
