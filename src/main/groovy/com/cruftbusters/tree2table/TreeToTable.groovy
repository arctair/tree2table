package com.cruftbusters.tree2table

class TreeToTable {
  List<List> convert(List<Map> tree) {
    List<List> rows = []
    for (Map node : tree){
      (rows.size()..<getDepth(node)).each {rows.add([])}
      rows[0] += unlinkChildren(node)
      if (node.children) {
        rows[1] += node.children
      }
    }
    rows
  }

  private static Map unlinkChildren(Map node) {
    [:].tap {
      it.putAll(node)
      it.remove('children')
    }
  }

  private static int getDepth(Map node) {
    node.children ? 2 : 1
  }
}
