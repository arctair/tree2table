package com.cruftbusters.tree2table

class TreeToTable {
  List<List> convert(List<Map> tree) {
    horizontalJoinSlices(tree.collect(this.&makeSlice))
  }

  private static List<List> horizontalJoinSlices(List slices) {
    int maxHeight = slices*.size().max()
    slices = slices.collect { this.&setHeight(it, maxHeight) }
    (0..<maxHeight).collect { height -> slices*.getAt(height).flatten() }
  }

  private static List<List> makeSlice(Map node) {
    List rows = node.children
      ? horizontalJoinSlices(node.children.collect(this.&makeSlice))
      : []
    [unlinkChildren(node), *rows]
  }

  private static List setHeight(List slice, int height) {
    [].tap { next ->
      addAll(slice)
      (size()..<height).each { add([]) }
    }
  }

  private static Map unlinkChildren(Map node) {
    [:].tap {
      it.putAll(node)
      it.remove('children')
    }
  }
}
