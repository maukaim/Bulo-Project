import 'package:bulo_ui/widgets/main_window/nav/nav_tab/nav_menu_type.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final selectedTabMenuProvider = StateProvider<NavMenuType?>((ref) => NavMenuType.flowMenu);