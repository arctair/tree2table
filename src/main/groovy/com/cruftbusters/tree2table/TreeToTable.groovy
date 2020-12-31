package com.cruftbusters.tree2table

class TreeToTable {
  List<List> convert(List<Map> tree) {
    joinSlices(tree.collect(this.&makeSlice))
  }

  private List<List> joinSlices(List slices) {
    int maxHeight = slices*.size().max()
    slices = slices.collect{this.&setHeight(it, maxHeight)}
    (0..<maxHeight).collect{height-> slices*.getAt(height).flatten()}
  }

  private static List<List> makeSlice(Map node) {
    List<List<Map>> rows = (0..<getDepth(node)).collect {[]}
    rows[0] += unlinkChildren(node)
    if (node.children) {
      rows[1] += node.children
    }
    rows
  }

  private static List setHeight(List slice, int height) {
    [].tap {next->
      addAll(slice)
      (size()..<height).each{add([])}
    }
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
