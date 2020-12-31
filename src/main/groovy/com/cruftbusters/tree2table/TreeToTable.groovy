package com.cruftbusters.tree2table

class TreeToTable {
  static List<List> convert(Map root) {
    List rows = root.children
      ? horizontalJoinSlices(root)
      : []
    int width = rows.size() > 0
      ? rows.last().size()
      : 1
    root = width > 1
        ? [*:root, width: width]
        : root
    root.name ? [unlinkChildren(root), *rows] : rows
  }

  private static List<List> horizontalJoinSlices(Map node) {
    List slices = node.children.collect(this.&convert)
    int maxHeight = slices*.size().max()
    slices = slices.collect { this.&setHeight(it, maxHeight) }
    (0..<maxHeight).collect { height -> slices*.getAt(height).flatten() }
  }

  private static List setHeight(List slice, int height) {
    [].tap { next ->
      (slice.size()..<height).each { add([[:]]) }
      addAll(slice)
    }
  }

  private static Map unlinkChildren(Map node) {
    [:].tap {
      it.putAll(node)
      it.remove('children')
    }
  }
}
