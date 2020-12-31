package com.cruftbusters.tree2table

class TreeToTable {
  List<List> convert(List<Map> tree) {
    List<List> rows = []
    for (Map node : tree){
      if (rows.size() < 1) {
        rows.add([])
      }
      rows[0] += unlinkChildren(node)
    }
    rows
  }

  private static Map unlinkChildren(Map node) {
    [:].tap {
      it.putAll(node)
      it.remove('children')
    }
  }
}
