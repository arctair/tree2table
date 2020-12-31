package com.cruftbusters.tree2table

class TreeToTable {
  static List<List> createTable(Map root) {
    List rows = root.children
      ? joinTables(root)
      : []
    int width = rows.size() > 0
      ? rows.last().size()
      : 1
    root = width > 1
      ? [*: root, width: width]
      : root
    root.name ? [unlinkChildren(root), *rows] : rows
  }

  private static List<List> joinTables(Map node) {
    List slices = node.children.collect(this.&createTable)
    int maxHeight = slices*.size().max()
    List paddedSlices = slices.collect { this.&padTop(it, maxHeight) }
    (0..<maxHeight).collect { height -> paddedSlices*.getAt(height).flatten() }
  }

  private static List padTop(List slice, int height) {
    whitespace(slice.last().size(), height - slice.size()) + slice
  }

  private static List whitespace(int width, int height) {
    (0..<height).collect {
      (0..<width).collect { [:] }
    }
  }

  private static Map unlinkChildren(Map node) {
    [:].tap {
      it.putAll(node)
      it.remove('children')
    }
  }
}
