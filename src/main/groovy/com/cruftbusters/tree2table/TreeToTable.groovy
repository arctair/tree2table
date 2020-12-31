package com.cruftbusters.tree2table

class TreeToTable {
  static List<List<Map>> createTable(Map root) {
    List rows = root.children
      ? joinTables(root.children.collect(this.&createTable))
      : []
    int width = rows.size() > 0
      ? rows.last().size()
      : 1
    root = width > 1
      ? [*: root, width: width]
      : root
    root.name ? [unlinkChildren(root), *rows] : rows
  }

  private static List<List<Map>> joinTables(List<List<List<Map>>> tables) {
    int height = tables*.size().max()
    List topPaddedTables = tables.collect { List<List> table ->
      padding(
        table.last().size(),
        height - table.size(),
      ) + table
    }
    (0..<height)
      .collect { rowIndex -> topPaddedTables*.getAt(rowIndex).flatten() }
  }

  private static List<List<Map>> padding(int width, int height) {
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
