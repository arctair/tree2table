package com.cruftbusters.tree2table

class TreeToTable {
  static List<List> convert(Map root) {
    [
      *(root.name
        ? [unlinkChildren(root)]
        : []),
      *root.children
        ? horizontalJoinSlices(root)
        : [],
    ]
  }

  private static List<List> horizontalJoinSlices(Map node) {
    List slices = node.children.collect(this.&convert)
    int maxHeight = slices*.size().max()
    slices = slices.collect { this.&setHeight(it, maxHeight) }
    (0..<maxHeight).collect { height -> slices*.getAt(height).flatten() }
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
