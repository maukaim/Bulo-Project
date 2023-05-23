import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MainContent extends StatelessWidget{
  const MainContent({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(4, 10, 10, 10),
      child: Container(
        decoration: const BoxDecoration(
            boxShadow: [
              BoxShadow(
                offset: Offset(-4, -4),
                spreadRadius: -3,
                blurRadius: 4,
                color: Color.fromRGBO(0, 0, 0, 0.15),
              ),
              BoxShadow(
                offset: Offset(4, 4),
                spreadRadius: -3,
                blurRadius: 4,
                color: Color.fromRGBO(0, 0, 0, 0.15),
              ),
              BoxShadow(
                offset: Offset(-4, 4),
                spreadRadius: -3,
                blurRadius: 4,
                color: Color.fromRGBO(0, 0, 0, 0.15),
              ),
              BoxShadow(
                offset: Offset(4, -4),
                spreadRadius: -3,
                blurRadius: 4,
                color: Color.fromRGBO(0, 0, 0, 0.15),
              ),
            ],
            color: Colors.white,
            borderRadius: BorderRadius.all(Radius.circular(8))),
      ),
    );
  }

}